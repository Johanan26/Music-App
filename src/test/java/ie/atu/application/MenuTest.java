package ie.atu.application;

import ie.atu.jdbc.application.Menu;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MenuTest {
        Menu newMenu = new Menu();
        @Test
        void testDisplayMenu(){
                String[] actMenu = Menu.displayMenu();

                // Then
                String[] expMenu = {"1. Home\n2. Search\n3. Library\n4. Settings\n5. Logout"};
                assertArrayEquals(expMenu, actMenu);
                assertEquals(Arrays.toString(expMenu), Arrays.toString(actMenu));
        }

        private void assertEquals(String expMenu, String actMenu) {
        }

}
