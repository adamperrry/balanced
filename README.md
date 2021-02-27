# AIM Java Web Course

## Capstone Project: balanced.

### A Personal Finance Web Application by Adam Perry (github: adamperrry)

---

&nbsp;

## Project Description

Balanced is a hands-on personal finance application built on the idea that your finances are better left in your own hands. This app is perfect for the person that wants to keep detailed tabs on their transactions but doesn't want to be pigeon-holed by hands-off, opinionated services.

YOU log your own transactions, YOU decide how to categorize them, and YOU know the state of all your accounts, all in one app.

The downside is: the onus is on your diligence.
The upside: a detailed record of your financial history and a more intimate relationship with how you spend and receive money.

Compare the balances on your app with the balances in the bank, and
you'll be the FIRST to know if something is amiss.

---

&nbsp;

## Tech Stack

### **Back End**

The back end of this project is a semi-RESTful API written in Java, built using Apache Maven 3.6.3 (build automation, dependency management, and structure) and Spring-Boot (MVC web app framework), utilizing Spring-Boot's default JPA (Java Persistence API) implementation, Hibernate, as an ORM (object-relational mapping) model.

MySql was used as the database. Included in the "backend" folder of this project are the MySQL scripts for setting up the database. Feel free to study the database structure and create an analagous API - as long is it serves the front end with the correct objects in JSON!

I will likely attempt to build a similar back end using Ruby on Rails in the near future.

### **Front End**

The front end of this project was built using Vue.js (Vue 3 with Router and Vuex) and TailwindCSS.

All data - including transactions, accounts, methods, settings, etc. - is retrieved from the back end API only once on initial app mounting. This data is stored in a Vuex data store accessible to all components. This data store loads the data initially, and when necessary, submits POST, DELETE, and PUT requests while simultaneously updating the data store, making subsequent GET requests unecessary.

I have no idea if this single GET request method makes the application faster or slower. The front end contains a lot of javascript manipulation of the data, so it's possible offloading some of this to the server may speed things up, but that would make the back-end more complex.

The up side is high portability to a different back end service whose sole responsibility is direct mapping of data to and from a database without manipulation in a service layer. This is my first attempt at a fully realized full stack application, so performance isn't too high on my list currently. I would also like to refactor some of the more complex components (such as Transactions.vue) to make use of Vue 3's new Composition API to better organize logical concerns.

---

&nbsp;

## Installation/Setup

### **Back End**

This project is built with and requires a Maven installation. After installing Maven, navigate to the Balanced or Backend folder in the terminal and run the following command:

```
mvn spring-boot:run
```

This will run a Tomcat server open on localhost port 8080 by default. Once running, the api can be tested in the browser at http://localhost:8080/api/transactions

### **Front End**

**SPIN UP THE FRONT END AFTER THE BACK END**

Spinning up the development server requires Node.js and npm (installed with Node). Once npm is installed, navigate to the frontend-vue directory in the terminal and run the following command:

```
npm install
```

This will install all dependencies and development dependencies listed in package.json - when dependencies are isntalled, while still in the frontend-vue directory, run the following:

```
npm run serve
```

This will spin up a hot reloading development server accessible via the links in the terminal. "npm run build" will run without devDependencies.

---

&nbsp;

## Future Plans

- Finish all pages, including Accounts, Summaries, Categories
- I hope to build out this backend using Ruby on Rails
- Add authorization for user-specific data/accounts
  - This app and database is currently self-contained, and data is not user-specific
