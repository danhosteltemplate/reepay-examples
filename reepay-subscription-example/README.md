# Reepay API examples

Example project showing samples of the Reepay API use. The project is written in Java using the Spring Boot framework.

## Prerequisites

* Java 8
* Maven 3.x

## Building

    mvn clean install

## Running

A public and a private key must be provided as well as the handle for the plan you wish to test this example on.

    mvn spring-boot:run -Dpublic_key=pub_a14c9c530076ac4abfcbdded1fc68dbc -Dprivate_key=priv_105b4a558fdf057833e31ec80b9572fe -Dplan_handle=myplanhandle
    
If the application does not automatically open in your browser, go to http://localhost:8080/

## The application
### Registering
The register page will show you a form. All fields are required.
When you register, a customer will be created in the local database and the customer will be subscribed to the plan through Reepay.
- **There are no one-to-one mapping between the local database and Reepay. The local database is reset everytime the application is restarted**
- **There are no length or complexity restriction on the password**
- **In this example, you can't have two customers registered with the same email address**

## Registered customers
When one or more customers are registered they will be shown under the tab "Registered customers"
- **This tab is for testing purposes only. You should never expose such data**

## Signing in to my page
When you have registered you can sign in and view various details.
If you click on the subscription handle you should be able to view information about the plan to which you are subscribed.
Here you can unsubscribe, add a coupon to your subscription and iff you click on the subscription handle again, you will be able to view all your invoices, and information about the card you have associated with the subscription.
