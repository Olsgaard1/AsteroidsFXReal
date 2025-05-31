package dk.sdu.cbse.main;

import dk.sdu.cbse.Common.data.Entity;
import dk.sdu.cbse.Common.data.GameData;
import dk.sdu.cbse.Common.data.GameKeys;
import dk.sdu.cbse.Common.data.World;
import dk.sdu.cbse.Common.services.IEntityProcessingService;
import dk.sdu.cbse.Common.services.IGamePluginService;
import dk.sdu.cbse.Common.services.IPostEntityProcessingService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {


    long Lasttime = System.nanoTime();

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<IGamePluginService> gamePluginServices;


    private final Text asteroidText = new Text("Asteroids destroyed: 0");

    Game(List<IEntityProcessingService> entityProcessingServices,List<IPostEntityProcessingService> postEntityProcessingServices,List<IGamePluginService> gamePluginServices) {
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.gamePluginServices = gamePluginServices;
    }


    private List<Text> addAsteroidText(List<String> labels) {
        List<Text> texts = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            texts.add(new Text(10,20 + i * 20,labels.get(i)));
        }
        return texts;
    }

    public void start(Stage window) throws Exception {
        asteroidText.setX(10);
        asteroidText.setY(10);

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());

        gameWindow.getChildren().add(asteroidText);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePluginServices) {
            iGamePlugin.StartGame(gameData, world);
        }
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
        render();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double delta =(now - Lasttime) / 1e9;
                Lasttime = now;

                gameData.setDelta(delta * 0.5);

                update();
                asteroidText.setText("Destroyed asteroids:" + gameData.getAsteroidsDestroyed());
                draw();
                gameData.getKeys().update();
            }

        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }

        List<Node> nodestoRemove = new ArrayList<>(gameWindow.getChildren().filtered(node -> node instanceof Text));
        gameWindow.getChildren().removeAll(nodestoRemove);
        gameWindow.getChildren().addAll(addAsteroidText(gameData.getLabels()));
    }

    private void draw() {
        for (Entity polygonEntity : polygons.keySet()) {
            if (!world.getEntities().contains(polygonEntity)) {
                Polygon removed = polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removed);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }


    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServices;
    }
    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }
    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

}
