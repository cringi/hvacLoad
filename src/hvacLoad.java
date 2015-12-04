/**
 * hvac load
 * another thing from brandon berney
 */

import java.util.Scanner;

public class hvacLoad {
    // Constants
    private static final double
            SUNSHINE = 193,
            WINTER_INDOOR_TEMP = 72,
            SUMMER_INDOOR_TEMP = 76,
            SUMMER_INDOOR_HUMIDITY = 0.01,
            MORNING_WINTER_MULTIPLIER = 1.3,
            SUMMER_MULTIPLIER = 1.1,
            TON = 12000;
    private static Scanner input = new Scanner(System.in);
    private static double
            roof_area, // <User Inputs>
            building_perimeter,
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
            electricity_use, // </User Inputs>
            winter_roof_load, // <Winter Things>
            winter_window_load,
            winter_wall_load,
            winter_infiltration_load,
            winter_temp_diff, // </Winter Things>
            summer_roof_load, // <Summer Things>
            summer_window_load,
            summer_wall_load,
            summer_infiltration_temp_load,
            summer_infiltration_humidity_load,
            summer_solar_load,
            summer_electrical_load,
            summer_people_temp_load,
            summer_people_humidity_load,
            summer_temp_diff,
            summer_humidity_diff, // </Summer Things>
            area_total, // <etc>
            net_wall_area,
            heating_btu,
            cooling_btu,
            cooling_load; // </etc>

    public static void main(String[] args) {
        // This is temporary
        /* roof_area = 1500;
        building_perimeter = 140;
        building_height = 18;
        doors_windows_total_area = 400;
        west_windows_area = 80;
        roof_u_value = 0.04;
        wall_u_value = 0.10;
        doors_windows_u_value = 0.5;
        winter_outdoor_temp = 2;
        summer_outdoor_temp = 100;
        summer_outdoor_humidity = 0.013;
        infiltration_rate = 200;
        people = 3;
        electricity_use = 1500; */

        // This is also temporary
        getInputs();
        calculations();
        System.out.println(results());
    }

    private static void getInputs() {
        // Holy moly there are a lot here
        System.out.print("Total roof area: ");
        roof_area = input.nextDouble();

        System.out.print("Building perimeter (ft): ");
        building_perimeter = input.nextDouble();

        System.out.print("Building height (ft): ");
        building_height = input.nextDouble();

        System.out.print("Total area of doors and windows (ft): ");
        doors_windows_total_area = input.nextDouble();

        System.out.print("Total area of west-facing windows (ft): ");
        west_windows_area = input.nextDouble();

        System.out.print("Roof U-value: ");
        roof_u_value = input.nextDouble();

        System.out.print("Wall U-value: ");
        wall_u_value = input.nextDouble();

        System.out.print("Window U-value: ");
        doors_windows_u_value = input.nextDouble();

        System.out.print("Summer outdoor temperature (F): ");
        summer_outdoor_temp = input.nextDouble();

        System.out.print("Summer outdoor humidity ratio: ");
        summer_outdoor_humidity = input.nextDouble();

        System.out.print("Winter outdoor temperature (F): ");
        winter_outdoor_temp = input.nextDouble();

        System.out.print("Infiltration rate (CFM): ");
        infiltration_rate = input.nextDouble();

        System.out.print("Number of people present: ");
        people = input.nextDouble();

        System.out.print("Electricity in use (watts): ");
        electricity_use = input.nextDouble();
    }

    private static void calculations() {
        area_total = building_perimeter * building_height;

        // Heating load
        winter_temp_diff = WINTER_INDOOR_TEMP - winter_outdoor_temp;
        net_wall_area = area_total - doors_windows_total_area;

        winter_roof_load = roof_area * roof_u_value * winter_temp_diff;
        winter_window_load = doors_windows_total_area * doors_windows_u_value * winter_temp_diff;
        winter_wall_load = net_wall_area * wall_u_value * winter_temp_diff;
        winter_infiltration_load = infiltration_rate * 1.08 * winter_temp_diff;

        heating_btu = (winter_infiltration_load + winter_roof_load + winter_wall_load + winter_window_load) *
                MORNING_WINTER_MULTIPLIER;

        // Cooling load
        summer_temp_diff = summer_outdoor_temp - SUMMER_INDOOR_TEMP;
        summer_humidity_diff = summer_outdoor_humidity - SUMMER_INDOOR_HUMIDITY;

        summer_roof_load = roof_area * roof_u_value * summer_temp_diff;
        summer_window_load = doors_windows_total_area * doors_windows_u_value * summer_temp_diff;
        summer_wall_load = net_wall_area * wall_u_value * summer_temp_diff;
        summer_infiltration_temp_load = infiltration_rate * 1.08 * summer_temp_diff;
        summer_infiltration_humidity_load = infiltration_rate * 4675 * summer_humidity_diff;
        summer_solar_load = SUNSHINE * west_windows_area;
        summer_electrical_load = 3.416 * electricity_use;
        summer_people_temp_load = 250 * people;
        summer_people_humidity_load = 200 * people;

        cooling_btu = (summer_roof_load + summer_window_load + summer_wall_load + summer_infiltration_humidity_load +
                summer_infiltration_temp_load + summer_people_temp_load + summer_people_humidity_load +
                summer_electrical_load + summer_solar_load) * SUMMER_MULTIPLIER;
        cooling_load = Math.round(cooling_btu / TON);
    }

    private static String results() {
        String result = String.format("Sunshine: %g%n"
                        + "Winter indoor temperature: %g%n"
                        + "Summer indoor temperature: %g%n"
                        + "Summer indoor humidity: %g%n"
                        + "Morning winter multiplier: %g%n"
                        + "Morning summer multiplier: %g%n"
                        + "One ton of BTU: %g%n"

                        + "%nRoof Area: %g%n"
                        + "Building perimeter: %g%n"
                        + "Building Height: %g%n"
                        + "Total Area of Doors and Windows: %g%n"
                        + "Area of West Facing Windows: %g%n"
                        + "Roof U-value: %g%n"
                        + "Wall U-value: %g%n"
                        + "Door and Windows U-value: %g%n"
                        + "Winter Outdoor Temperature: %g%n"
                        + "Summer Outdoor Temperature: %g%n"
                        + "Summer Outdoor Humidity: %g%n"
                        + "Infiltration Rate: %g%n"
                        + "People present: %g%n"
                        + "Electricity use: %g watts%n"

                        + "%nTotal area: %g%n"
                        + "Net wall area: %g%n"

                        + "%nWinter temperature difference: %g%n"
                        + "Net wall area: %g%n"
                        + "Winter roof load: %g%n"
                        + "Winter window load: %g%n"
                        + "Winter wall load: %g%n"
                        + "Winter infiltration load: %g%n"

                        + "%nSummer temperature difference: %g%n"
                        + "Summer humidity difference: %g%n"
                        + "Summer roof load: %g%n"
                        + "Summer window load: %g%n"
                        + "Summer wall load: %g%n"
                        + "Summer infiltration temperature load: %g%n"
                        + "Summer infiltration humidity load: %g%n"
                        + "Summer solar load: %g%n"
                        + "Summer electrical load: %g%n"
                        + "Summer people temperature load: %g%n"
                        + "Summer people humidity load: %g%n"

                        + "%nHeating Load: %g BTU/hr%n"
                        + "Cooling Load: %g BTU/hr [~%s tons]", SUNSHINE, WINTER_INDOOR_TEMP, SUMMER_INDOOR_TEMP,
                SUMMER_INDOOR_HUMIDITY, MORNING_WINTER_MULTIPLIER, SUMMER_MULTIPLIER, TON, roof_area,
                building_perimeter, building_height, doors_windows_total_area, west_windows_area, roof_u_value,
                wall_u_value, doors_windows_u_value, winter_outdoor_temp, summer_outdoor_temp, summer_outdoor_humidity,
                infiltration_rate, people, electricity_use, area_total, net_wall_area, winter_temp_diff,
                winter_roof_load, winter_window_load, winter_window_load, winter_wall_load, winter_infiltration_load,
                summer_temp_diff, summer_humidity_diff, summer_roof_load, summer_window_load, summer_wall_load,
                summer_infiltration_temp_load, summer_infiltration_humidity_load, summer_solar_load,
                summer_electrical_load, summer_people_temp_load, summer_people_humidity_load, heating_btu, cooling_btu,
                cooling_load);
        return result;
    }
}
