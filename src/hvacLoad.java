/**
 * hvac load
 * another thing from brandon berney
 */

public class hvacLoad {
    private static input=new

    // Constants
    private static final double
            SUNSHINE = 193,
            WINTER_INDOOR_TEMP = 72,
            SUMMER_INDOOR_TEMP = 76,
            SUMMER_INDOOR_HUMIDITY = 0.01,
            MORNING_WINTER_MULTIPLIER = 1.3,
            MORNING_SUMMER_MULTIPLIER = 1.1,
            TON = 12000;
    // User inputs
    private static double
            roof_area,
            building_permiter,
            building_height,
            doors_windows_total_area,
            west_windows_area,
            roof_u_value,
            wall_u_value,
            doors_windows_u_value,
            winter_outdoor_temp,
            summer_outdoor_temp,
            summer_outdoor_humidity,
            infiltration_rate,
            people,
            electricity_use;
    // Winter things
    private static double
            winter_roof_load,
            winter_window_load,
            winter_wall_load,
            winter_infiltration_load,
            winter_temp_diff;
    // Summer things
    private static double
            summer_roof_load,
            summer_window_load,
            summer_wall_load,
            summer_infiltration_temp_load,
            summer_infiltration_humidity_load,
            summer_solar_load,
            summer_electrical_load,
            summer_people_temp_load,
            summer_people_humidity_load,
            summer_temp_diff,
            summer_humidity_diff;
    // Other things used by the program
    private static double
            area_total,
            net_wall_area,
            heating_btu,
            cooling_btu,
            cooling_load;

    Scanner(System.in);

    public static void main(String[] args) {
        // This is temporary
        roof_area = 1500;
        building_permiter = 140;
        building_height = 18;
        doors_windows_total_area = 400;
        west_windows_area = 80;
        roof_u_value = 0.04;
        wall_u_value = 0.10;
        doors_windows_u_value = 0.5;
        winter_outdoor_temp = 2
        summer_outdoor_temp = 100
        summer_outdoor_humidity = 0.013;
        infiltration_rate = 200;
        people = 3;
        electricity_use = 1500;

        // This is also temporary
        // getInputs();
        calculations();
        System.out.println(results());
    }

    private static void calculations() {
        area_total = building_permiter * building_height;

        // Heating load
        winter_temp_diff = WINTER_INDOOR_TEMP - winter_outdoor_temp;
        net_wall_area = area_total - doors_windows_total_area;

        winter_roof_load = roof_area * roof_u_value * winter_temp_diff;
        winter_window_load = roof_area * doors_windows_u_value * winter_temp_diff;
        winter_wall_load = net_wall_area * wall_u_value * winter_temp_diff;
        winter_infiltration_load = infiltration_rate * 1.08 * winter_temp_diff;

        // Cooling load
        summer_temp_diff = summer_outdoor_temp - SUMMER_INDOOR_TEMP;
        summer_humidity_diff = summer_outdoor_humidity - SUMMER_INDOOR_HUMIDITY;

        summer_roof_load = roof_area * roof_u_value * summer_temp_diff;
        summer_window_load = roof_area * doors_windows_u_value * summer_temp_diff;
        summer_wall_load = net_wall_area * wall_u_value * summer_temp_diff;
        summer_infiltration_temp_load = infiltration_rate * 1.08 * summer_temp_diff;
        summer_infiltration_humidity_load = infiltration_rate * 4675 * summer_humidity_diff;
        summer_solar_load = SUNSHINE * west_windows_area;
        summer_electrical_load = 3.416 * electricity_use;
        summer_people_temp_load = 250 * people;
        summer_people_humidity_load = 200 * people;
    }

    private static String results() {
        String magic = String.format("Sunshine: %f%n"
                        + "Winter indoor temperature: %f%n"
                        + "Summer indoor temperature: %f%n"
                        + "Summer indoor humidity: %f%n"
                        + "Morning winter multiplier: %f%n"
                        + "Morning summer multiplier: %f%n"
                        + "One ton of BTU: %f%n"

                        + "%nRoof Area: %f%n"
                        + "Building Permiter: %f%n"
                        + "Building Height: %f%n"
                        + "Total Area of Doors and Windows: %f%n"
                        + "Area of West Facing Windows: %f%n"
                        + "Roof U-value: %f%n"
                        + "Wall U-value: %f%n"
                        + "Door and Windows U-value: %f%n"
                        + "Winter Outdoor Temperature: %f%n"
                        + "Summer Outdoor Temperature: %f%n"
                        + "Summer Outdoor Humidity: %f%n"
                        + "Infiltration Rate: %f%n"
                        + "People present: %f%n"
                        + "Electricity use: %f watts%n"
                        + "Total Area: %f%n"

                        + "%nWinter temperature difference: %f%n"
                        + "Net wall area: %f%n"
                        + "Winter roof load: %f%n"
                        + "Winter window load: %f%n"
                        + "Winter wall load: %f%n"
                        + "Winter infiltration load: %f%n"

                        + "%nSummer temperature difference: %f%n"
                        + "Summer humidity difference: %f%n"
                        + "Summer roof load: %f%n"
                        + "Summer window load: %f%n"
                        + "Summer wall load: %f%n"
                        + "Summer infiltration temperature load: %f%n"
                        + "Summer infiltration humidity load: %f%n"
                        + "Summer solar load: %f%n"
                        + "Summer electrical load: %f%n"
                        + "Summer people temperature load: %f%n"
                        + "Summer people humidity load: %f%n"

                        + "%nHeating Load: %f%n"
                        + "Cooling Load: %f [~%f tons]", SUNSHINE, WINTER_INDOOR_TEMP, SUMMER_INDOOR_TEMP, SUMMER_INDOOR_HUMIDITY,
                MORNING_WINTER_MULTIPLIER, MORNING_SUMMER_MULTIPLIER, TON, roof_area, building_permiter, building_height,
                doors_windows_total_area, west_windows_area, roof_u_value, wall_u_value, doors_windows_u_value,
                winter_outdoor_temp, summer_outdoor_temp, summer_outdoor_humidity, infiltration_rate, people, electricity_use,
                area_total, winter_temp_diff, winter_roof_load, winter_window_load, winter_window_load, winter_wall_load,
                winter_infiltration_load, summer_temp_diff, summer_humidity_diff, summer_roof_load, summer_window_load,
                summer_wall_load, summer_infiltration_temp_load, summer_infiltration_humidity_load, summer_solar_load,
                summer_electrical_load, summer_people_temp_load, summer_people_humidity_load, heating_btu, cooling_btu,
                cooling_load);
        return magic;
    }

    private static void getInputs() {
        // Holy moly there are a lot here
        System.out.print("Total roof area: ");
        roof_area = input.nextDouble();

        System.out.print("Building permiter: ");
        building_permiter = input.nextDouble();

        System.out.print("Building height: ");
        building_height = input.nextDouble();

        System.out.print("Total area of doors and windows: ");
        doors_windows_total_area = input.nextDouble();

        System.out.print("Total area of west-facing windows: ");
        west_windows_area = input.nextDouble();

        System.out.print("Roof U-value: ");
        roof_u_value = input.nextDouble();

        System.out.print("Wall U-value: ");
        wall_u_value = input.nextDouble();

        System.out.print("Winter outdoor design temperature: ");
        winter_outdoor_temp = input.nextDouble();

        System.out.print("Summer outdoor design temperature: ");
        summer_outdoor_temp = input.nextDouble();

        System.out.print("Summer outdoor humidity ratio: ");
        summer_outdoor_humidity = input.nextDouble();

        System.out.print("Infiltration rate (CFM): ");
        infiltration_rate = input.nextDouble();

        System.out.print("Number of people present: ");
        people = input.nextDouble();

        System.out.print("Electricity in use: ");
        electricity_use = input.nextDouble();
    }
}
