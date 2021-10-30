package main.classes.users;

import main.enums.UserType;

public class Regular extends User{
    private String tenantPropertyId;

    public Regular(String id, String firstName, String lastName, String username, String email, String password, String address, String tenantPropertyId) {
        super(id, UserType.REGULAR, firstName, lastName, username, email, password, address);
        this.tenantPropertyId = tenantPropertyId;
    }

    public String getTenantPropertyId(){ return tenantPropertyId; }

    public void setTenantPropertyId(String tenantPropertyId) { this.tenantPropertyId=tenantPropertyId; }
}
