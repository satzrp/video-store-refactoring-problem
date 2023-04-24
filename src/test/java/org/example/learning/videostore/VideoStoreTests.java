package org.example.learning.videostore;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class VideoStoreTests {

    @Nested
    @DisplayName("test customer statement for children movies")
    class ChildrenMovieTests {
        @Test
        void rental_for_less_than_3_days() {
            Movie childrenMovie = new Movie("Children Movie One", 2);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(childrenMovie, 1));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Children Movie One	1.5
                    Amount owed is 1.5
                    You earned 1 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void rental_for_more_than_3_days() {
            Movie childrenMovie = new Movie("Children Movie One", 2);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(childrenMovie, 4));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Children Movie One	3.0
                    Amount owed is 3.0
                    You earned 1 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void for_multiple_rentals() {
            Movie childrenMovieOne = new Movie("Children Movie One", 2);
            Movie childrenMovieTwo = new Movie("Children Movie Two", 2);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(childrenMovieOne, 4));
            customer.addRental(new Rental(childrenMovieTwo, 1));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Children Movie One	3.0
                    	Children Movie Two	1.5
                    Amount owed is 4.5
                    You earned 2 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }
    }

    @Nested
    @DisplayName("test customer statement for new releases")
    class NewReleaseTests {
        @Test
        void rental_for_less_than_1_day() {
            Movie newRelease = new Movie("New Release One", 1);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(newRelease, 1));
            String expectedStatement = """
                    Rental Record for Customer One
                    	New Release One	3.0
                    Amount owed is 3.0
                    You earned 1 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void rental_for_more_than_1_day() {
            Movie newRelease = new Movie("New Release One", 1);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(newRelease, 2));
            String expectedStatement = """
                    Rental Record for Customer One
                    	New Release One	6.0
                    Amount owed is 6.0
                    You earned 2 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void multiple_new_release_rentals() {
            Movie newReleaseOne = new Movie("New Release One", 1);
            Movie newReleaseTwo = new Movie("New Release Two", 1);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(newReleaseOne, 1));
            customer.addRental(new Rental(newReleaseTwo, 2));
            String expectedStatement = """
                    Rental Record for Customer One
                    	New Release One	3.0
                    	New Release Two	6.0
                    Amount owed is 9.0
                    You earned 3 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }
    }

    @Nested
    @DisplayName("test customer statement for regular movies")
    class RegularMovieTests {
        @Test
        void rental_for_less_than_2_days() {
            Movie regularMovie = new Movie("Regular Movie One", 0);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(regularMovie, 1));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Regular Movie One	2.0
                    Amount owed is 2.0
                    You earned 1 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void rental_for_more_than_2_days() {
            Movie regularMovie = new Movie("Regular Movie One", 0);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(regularMovie, 3));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Regular Movie One	3.5
                    Amount owed is 3.5
                    You earned 1 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }

        @Test
        void rental_for_multiple_regular_movies() {
            Movie regularMovieOne = new Movie("Regular Movie One", 0);
            Movie regularMovieTwo = new Movie("Regular Movie Two", 0);
            Customer customer = new Customer("Customer One");
            customer.addRental(new Rental(regularMovieOne, 3));
            customer.addRental(new Rental(regularMovieTwo, 1));
            String expectedStatement = """
                    Rental Record for Customer One
                    	Regular Movie One	3.5
                    	Regular Movie Two	2.0
                    Amount owed is 5.5
                    You earned 2 frequent renter points""";
            assertThat(customer.statement()).isEqualTo(expectedStatement);
        }
    }

    @Test
    void test_statement_with_multiple_movies_of_different_price_types() {
        Customer customer = new Customer("Customer One");
        Movie regularMovie = new Movie("Regular Movie One", 0);
        Movie newRelease = new Movie("New Release One", 1);
        Movie childrenMovie = new Movie("Children Movie One", 2);
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(newRelease, 5));
        customer.addRental(new Rental(childrenMovie, 4));
        String expectedStatement = """
                Rental Record for Customer One
                	Regular Movie One	3.5
                	New Release One	15.0
                	Children Movie One	3.0
                Amount owed is 21.5
                You earned 4 frequent renter points""";
        assertThat(customer.statement()).isEqualTo(expectedStatement);
    }
}
