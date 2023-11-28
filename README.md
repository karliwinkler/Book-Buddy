# My Personal Project

## Phase 0

***What will the application do?***

The application will be a book tracking application that allows the user to track 
the books they’ve read recently, including their five-star rating of the book, 
any reviews they’ve left, and the genre. Using the application, users can 
rank their favourite books and authors, make lists of books they want to read, 
and set goals for how many books they’d like to read while comparing it to their 
current progress.

***Who will use it?***

Bookworms interested in keeping their book ratings/reviews in one place and 
tracking how many books they’ve read. The application would also work 
well for users looking to get more into reading, who want to set goals and 
track their progress.

***Why is this project of interest to you?***

I love to read, and I’ve always enjoyed being able to review the books I’ve 
read and share my thoughts with my friends. In the past, I’ve used an app called 
GoodReads to rate books, but I’ve found the app to be outdated and lacking in different 
functionalities. This project will be a fun opportunity to create the features and 
design choices that I would like to see in a book-tracking application as a reader 
myself and someone who would actually use an application like this.

## User Stories
-	As a user, I want to be able to add a book I’ve read to a list of my recently read books.
-	As a user, I want to be able to view a list of books I’ve read recently.
-	As a user, I want to be able to add a rating out of five stars to a book I’ve read.
-	As a user, I want to be able to see statistics of which genres and authors I’ve read the most of.


-   As a user, I want to be able to have the option to save the list of books I've read, my want to read list, 
and my favourites list to file.
-   As a user, I want to have the option to load my saved lists of books from file when I start the application.

## Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by adding a book
to your read list. Press the button on the main menu "view books I've read", then enter the title, author, and genre 
in the text fields at the bottom of the screen.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by adding a rating
and/or review to a book you've read. Double-click the book you'd like to rate from the list of read books and click 
the "rate book" or "review book" buttons on the right side panel. 
- You can locate my visual component by opening the application - you should see a gif of a book on the main menu. 
There is also a small image of an arrow on a button on the read list page.
- You can save the state of my application by clicking the "save books" button on the main menu.
- You can reload the state of my application by clicking the "load books" button on the main menu.

## Phase 4: Task 2
Example event log:

Mon Nov 27 18:29:29 PST 2023 \
Book added to read list. \
Mon Nov 27 18:29:36 PST 2023 \
Book rated 5 stars.\
Mon Nov 27 18:29:40 PST 2023\
Book reviewed.\
Mon Nov 27 18:29:46 PST 2023\
Book added to read list.\
Mon Nov 27 18:29:52 PST 2023\
Book rated 3 stars.\
Mon Nov 27 18:30:06 PST 2023\
Book collection saved to file.

## Phase 4: Task 3
If I had more time to improve my project, something I would focus on would be reducing coupling between the different 
"panel" classes (MainMenuPanel, ReadListPanel, ListPanel, BookInfoPanel). All these classes implement the PanelStyle 
interface, which stores fields of colours, fonts, and sizes, and most of them have very similar "set up" methods,
for setting up buttons, labels and colours. I would try using an abstract class instead of an interface
and refactoring some of these similar methods to be abstract methods. I would also change this abstract class so that 
instead of just storing colours, fonts, and sizes, there are concrete helper methods that set these for the panel,
so I don't have to pass these fields as parameters everytime.

Another change I would consider adding is implementing the observer method. As of now,
many of the panel classes have an "update" method that repaints the panel when the book collection is edited. Instead
of this,
I would make BookCollection the subject and make MainMenuPanel, ReadListPanel, and ListPanel the observers so that
they could be automatically updated whenever there is a change to the book collection. This would allow me to reduce 
coupling by removing some unnecessary associations, like ReadListPanel and MainMenuPanel both having a field 
of BookBuddyApp.
