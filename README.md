# JDBC-lite
A simple interphase with the JDBC for CRUD database tasks, this is in a very early stage of development

This is a version of this library for a specific use case, additional updates will be coming in the next few months that will allow 
more general applicability. 

The purpose of this library is to provide a Java beginner JDBC middleware to allow beginning programmers CRUD operations on a MySQL server
and database.

Feel free to tweek, or completely change it.

USAGE:

This allows you to create, read, update and delete databases, database tables, and table data of a MySQL database without ever handling 
any MySQL or JDBC. Simply provide it with an array of column names, an array of column types (only three supported at this poin) and an
array of data and the library will take care of the rest for you, from connection management to error handling.

The driver.java file provides an example use case.


