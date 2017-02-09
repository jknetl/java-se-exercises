package org.jknetl.javase.essential.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jknetl
 */
public class Exercise4 {

    private static final String PEOPLE_FILE = "essential-classes/basic-io/src/main/resources/people.out";

    public static void main(String[] args) {
        Person joe = new Person("Joe", "Tribiani", 25);

        Person john1 = new Person("John", "Doe", 44);
        Person john2 = new Person("John", "Doe", 44);

        // there are two different johns with same name and age
        System.out.println("john1.equals(john2) =" + john1.equals(john2));
        System.out.println("john1 is same as john2: " + (john2 == john1));

        // write joe and both johns into the stream
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(PEOPLE_FILE)))) {
            os.writeObject(joe);
            os.writeObject(john1);
            os.writeObject(john2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read people from stream
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(PEOPLE_FILE)))){
            Person p1 = (Person) is.readObject();
            Person p2 = (Person) is.readObject();
            Person p3 = (Person) is.readObject();

            System.out.println(p1.toString());
            System.out.println(p2.toString());
            System.out.println(p3.toString());

            // Are p2 and p3 different?
            System.out.println("p2.equals(p3) =" + p2.equals(p3));
            System.out.println("p2 is same as p3: " + (p2 == p3));

            //reading p4 fails with EOFException
            //Person p4 = (Person) is.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class Person implements Serializable{
        private String firstname;
        private String lastname;
        private int age;


        public Person(String firstname, String lastname, int age) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return age == person.age &&
                    Objects.equals(firstname, person.firstname) &&
                    Objects.equals(lastname, person.lastname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstname, lastname, age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

