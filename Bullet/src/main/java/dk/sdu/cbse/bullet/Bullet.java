package dk.sdu.cbse.bullet;

import dk.sdu.cbse.Common.data.Entity;

public class Bullet extends Entity {

    private String ownerType;

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerType() {
        return ownerType;
    }

}
