package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static Scanner sc = new Scanner(System.in);

    LocalDate createdate(String date)throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,formatter);
    }

    void createAuthorObject(Session session) {
        Author author = new Author();
        System.out.println("Enter first name");
        author.setFirstName(sc.next());
        System.out.println("Enter last name");
        author.setLastName(sc.next());
        System.out.println("Enter your age");
        author.setAge(sc.nextInt());
        System.out.println("Enter Date of birth as (yyyy-MM-dd)");
        try {
            author.setDob(createdate(sc.next()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        author.setAddress(createAddressofAuthor());
        author.setSubjects(createListOfSubject());
        author.setBook(AuthorsBook());
        session.save(author);
    }

    List createListOfSubject(){
        List list= new ArrayList();
        System.out.println("Enter the list of subjects");
        System.out.println("Enter how many subjects you want to add?");
        int size=sc.nextInt();
        for (int i=0;i<size;i++)
            list.add(sc.next());
        return list;
    }

    Book AuthorsBook(){
        Book book=new Book();
        System.out.println("Enter your book's name");
        book.setBookname(sc.next());
        return book;
    }

    Address createAddressofAuthor(){
        Address address= new Address();
        System.out.println("Enter Street Number");
        address.setStreetNo(sc.nextInt());
        sc.nextLine();
        System.out.println("Enter Location");
        address.setLocation(sc.nextLine());
        System.out.println("Enter State");
        address.setState(sc.nextLine());
        return address;
    }

    void readObject(Session session) {
        int row, innerch;
        String ch;
        System.out.println("Enter id of the Author you want to see");
        row = sc.nextInt();
        Author author;
        if (session.get(Author.class, row) == null) {
            System.out.println("No data at row " + row);
        } else {
            author = session.get(Author.class, row);
            System.out.println("Id: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
            System.out.println("Age: " + author.getAge());
            System.out.println("Date of Birth " + author.getDob());
            System.out.println("Address of the Author ");
            System.out.println("Street Number " + author.getAddress().getStreetNo());
            System.out.println("Location " + author.getAddress().getLocation());
            System.out.println("State " + author.getAddress().getState());
            for(int i=0;i<author.getSubjects().size();i++)
                System.out.println("Subject "+i+" " + author.getSubjects().get(i));
            System.out.println("Book written by you "+author.getBook().getBookname());
            System.out.println("Do you want to update. \n Press y to continue and n to exit");
            ch = sc.next();
            if (ch.equals("y")) {
                System.out.println("Press 1 to update First Name");
                System.out.println("Press 2 to update Last Name");
                System.out.println("Press 3 to update Age");
                System.out.println("Press 4 to update Date of Birth");
                System.out.println("Press 5 to update Street Number of the Author");
                System.out.println("Press 6 to update Location of the Author");
                System.out.println("Press 7 to update State of the Author");
                System.out.println("Press 8 to update Book written by author");
                innerch = sc.nextInt();
                switch (innerch) {
                    case 1:
                        String upfname;
                        System.out.println("Enter updated First Name");
                        upfname = sc.next();
                        author.setFirstName(upfname);
                        session.update(author);
                        break;
                    case 2:
                        String uplname;
                        System.out.println("Enter updated First Name");
                        uplname = sc.next();
                        author.setLastName(uplname);
                        session.update(author);
                        break;
                    case 3:
                        int upage;
                        System.out.println("Enter update age");
                        upage = sc.nextInt();
                        author.setAge(upage);
                        session.update(author);
                        break;
                    case 4:
                        System.out.println("Enter update Date of Birth");
                        String sdate = sc.next();
                        try {
                            author.setDob(createdate(sdate));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    case 5:
                        System.out.println("Enter updated Street Number");
                        int street=sc.nextInt();
                        author.getAddress().setStreetNo(street);
                        session.update(author);
                        break;
                    case 6:
                        System.out.println("Enter updated Location");
                        String location=sc.nextLine();
                        author.getAddress().setLocation(location);
                        session.update(author);
                        break;
                    case 7:
                        System.out.println("Enter updated State");
                        String state=sc.nextLine();
                        author.getAddress().setState(state);
                        session.update(author);
                    case 8:
                        System.out.println("Enter updated book name");
                        String book=sc.nextLine();
                        author.getBook().setBookname(book);
                        session.update(author);
                    default:
                        System.out.println("Enter from 1 to 6");
                }
        } else if (ch.equals("n")) {
                System.out.println("Do you want to delete it???\n Press y to delete and n to delete");
                String innerchs = sc.next();
                if (innerchs.equals("y")) {
                    if (session.load(Author.class, row) == null) {
                        System.out.println("No data at row " + row);
                    } else
                        session.delete(author);
                }
            }
        }
    }


    public static void main(String[] args) {
        Application app = new Application();
        String ch = "";
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        do {
            app.createAuthorObject(session);
            System.out.println("Press y for inserting more data");
            System.out.println("Press n to stop");
            ch = sc.next();
        } while (ch.equals("Y") || (ch.equals("y")));

        do {
            app.readObject(session);
            System.out.println("Press y for reading more data");
            System.out.println("Press n to stop");
            ch = sc.next();
        } while (ch.equals("Y") || (ch.equals("y")));


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        System.out.println("Hello World 1234 !!!!");
    }
}
