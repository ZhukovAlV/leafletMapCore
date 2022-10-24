package tetramap.route;

/**
 * Тип транспортного средства, используется для построения маршрута движения
 * Тип транспортного средства влияет на дороги, используемые для движения и на скорость движения
 */
public enum VehicleType {
    CAR("car");

    private final String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}