![reepay.js](https://docs.reepay.com/js/images/logo.png "reepay.js")

Example project demonstrating the use of the Reepay Token Javascript Library to get a credit card token that can be used for single charges and recurring payments for subscriptions.

The project contains a simple PHP web application served by a web server running in a Docker container. The sign-up page submits data to the server. The data can be used to perform a single charge, see this [example](https://github.com/reepay/reepay-examples/wiki/One-time-charging). Or it can be used to create a customer and a subscription, see this [example](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling#create-customer-and-subscription).

## Prerequisites

Docker and Docker Compose ([Docker Toolbox](https://www.docker.com/products/docker-toolbox))

## Running

1. Define the public key by substituting `{{your_publickeyhere}}` in `index.html`. A public key can be found/generated in the Reepay Administration under Developer -> API credentials.

2. Run a containerized web server using Docker Compose:

    `docker-compose up`

3. In a browser navigate to: http://localhost:8080/

## How it works

When you've entered all the information including opening the frame and entering credit card information, you can press on the sign up button. The data will then get dumped on the page you'll get redirected to.

We currently only support Danish and English.

## The examples

### Simple

The regular `index.html` shows the simplest configuration. We gennerate the button and the hidden input field for you. All you have to make sure to do is pick up the generated value in your form.

1. Include the script in your form in the place you want us to generate our button.
    ```html
    <script src="https://token.reepay.com/token.js"
        class="reepay-button"
        data-pubkey="{{your_publickeyhere}}"
        data-text="Card information"
        data-locale="en"
    </script>
    ```
    You can omit the language if you want it in Danish.

2. You're done! If you're simply posting a regular form, the value should be picked up as `reepay-token`


### Advanced

We know that some of our customers value the ability to customize stuff. So we made it possible to configure our frame in a more advanced way. You can see an example in the `index_advanced.html`

1. Include the script
    ```html
    <script src="https://token.reepay.com/token.js"
    ```
2. Instantiate the handler with a configure object. Here you can also bind callbacks. The `cardToken` is what will happen when you receive the token and the `ready` is if you want something to happen when our frame is ready to be opened. The bind of the click event listener is the binding to the open button so you can actually open our frame.
    ```js
        var handler = reepaytoken.configure({
            key: '{{your_publickeyhere}}',
            locale: 'da',
            cardToken: function(token) {
                console.log(token);
                document.querySelector('#token').value = token.id;
            },
            ready: function(){
                document.querySelector('#open-button').removeAttribute('disabled');
            }
        });

        document.querySelector('#open-button').addEventListener('click', function(event) {
            event.preventDefault();
            handler.open();
        });
    ```

## Testing

Changes to the HTML file will be reflected immediately when edited. Testing different errors can be triggered by using test cards and CVV combinations. See: https://docs.reepay.com/api/#testing

A list of error codes can be found here: https://docs.reepay.com/js/#errors

## References

[Reepay Token](https://docs.reepay.com/token/)

[Reepay API](https://docs.reepay.com/api/)

[Subscription Use Case](https://github.com/reepay/reepay-examples/wiki/Simple-subscription-handling)

[Charging Use Case](https://github.com/reepay/reepay-examples/wiki/One-time-charging)
