$.getScript("/scripts/lib/texts.js");

function ReepayUtils(valSettings){
	var self = this;
	// Settings which can extend/overwrite defaults by parsing an object as a parameter. See the HTML for example
	// Everything but the functions in the return block is private
	self.validationSettings = {
		cvvField: "#cvv",
		cardnumberField: "#cardnumber",
		expiryField: "#expiry",
		inputClass: ".customer_info",
		termsId: "#terms",
		formId: "#paymentform",
		checkEmail: true,
		submitProcess: false,
		locale: "en_GB"
	};
	$.extend( self.validationSettings, valSettings );

	// Validating that all required input fields are filled out
	self.validateInput = function(inputName){
		$(inputName).each(function() {
		    if ($(this).attr('required')) {

		        $(this).blur(function() {

		            if ($(this).val() == "") {
		                $(this).parent().removeClass("has-success");
		                $(this).parent().addClass("has-error");
		            } else {
		                $(this).parent().removeClass("has-error");
		                $(this).parent().addClass("has-success");
		            }

		        });

		    }
		});
	}

	// Check if the email fits the standard emai format
	self.isEmail = function(email) {
	    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    return regex.test(email);
	}

	// Validate that the customer info fields has been filled out
	self.validateCustomer = function(inputName) {
	    var status = true;

	    $(inputName).each(function() {

	        if ($(this).attr('required')) {
	            if ($(this).val() == "") {
	                console.log($(this).attr("name") + " is empty");
	                status = false;
	                return;
	            }
	        }

	        if ($(this).attr("name") == "email" && !self.isEmail($(this).val())) {
	            console.log($(this).attr("name") + " is not valid");
	            status = false;
	            return;
	        }
	    });
	    return status;
	}

	// Card number validation on HTML fields
	self.validateCardnumber = function(cardnumberField){
		$(cardnumberField).blur(function() {
            $('#error').hide();
            if (!reepay.validate.cardNumber($(cardnumberField).val())) {
            	// This can be chamged to how you wish to do with the validation of the cardnumberField

                if ($('#form-group-cardnumber').hasClass("has-success")) {
                    $('#form-group-cardnumber').toggleClass("has-success");
                }
                if (!$('#form-group-cardnumber').hasClass("has-error")) {
                    $('#form-group-cardnumber').toggleClass("has-error");
                }
            } else {
                if ($('#form-group-cardnumber').hasClass("has-error")) {
                    $('#form-group-cardnumber').toggleClass("has-error");
                }
                if (!$('#form-group-cardnumber').hasClass("has-success")) {
                    $('#form-group-cardnumber').toggleClass("has-success");
                }
            }
        });
	};
	self.validateExpiry = function(expiryField){
        $(expiryField).blur(function() {
            var exp = $(expiryField).val().replace(/\s/g, '').split("/");
            if (!reepay.validate.expiry(exp[0], exp[1])) {
            	// This can be chamged to how you wish to do with the validation of the cardnumberField
                if ($('#form-group-exp').hasClass("has-success")) {
                    $('#form-group-exp').toggleClass("has-success");
                }
                if (!$('#form-group-exp').hasClass("has-error")) {
                    $('#form-group-exp').toggleClass("has-error");
                }
            } else {
                if ($('#form-group-exp').hasClass("has-error")) {
                    $('#form-group-exp').toggleClass("has-error");
                }
                if (!$('#form-group-exp').hasClass("has-success")) {
                    $('#form-group-exp').toggleClass("has-success");
                }
            }
        });
    };
    self.validateCvv = function(cvvField){
    	$(cvvField).blur(function() {
            if (!reepay.validate.cvv($(cvvField).val())) {
            	// This can be changed to how you wish to do with the validation of the cardnumberField
                if ($('#form-group-cvc').hasClass("has-success")) {
                    $('#form-group-cvc').toggleClass("has-success");
                }
                if (!$('#form-group-cvc').hasClass("has-error")) {
                    $('#form-group-cvc').toggleClass("has-error");
                }
            } else {
                if ($('#form-group-cvc').hasClass("has-error")) {
                    $('#form-group-cvc').toggleClass("has-error");
                }
                if (!$('#form-group-cvc').hasClass("has-success")) {
                    $('#form-group-cvc').toggleClass("has-success");
                }
            }
        });
	}
	self.validateTerms = function(termsId){
		$(termsId).change(function() {
            // Check if terms has been accepted. This can be changed to how you wish to handle the errors. 
            if (this.checked) {
                $("#terms-group").removeClass("has-error");
                $("#terms-group").addClass("has-success");
            } else {
                $("#terms-group").removeClass("has-success");
                $("#terms-group").addClass("has-error");
            }
        });
	}

	// The public validate function
	self.validate = function(settings){
		self.validateInput(settings.inputClass);

		if(settings.checkEmail){
			$("input[name=email]").blur(function() {
			    if (!self.isEmail($(this).val())) {
			        $(this).parent().removeClass("has-success");
			        $(this).parent().addClass("has-error");
			    } else {
			        $(this).parent().removeClass("has-error");
			        $(this).parent().addClass("has-success");
				}

			});
		}
		self.validateCustomer(settings.inputClass);
		self.validateCardnumber(settings.cardnumberField);
		self.validateExpiry(settings.expiryField);
		self.validateCvv(settings.cvvField);
	}

	self.submitForm = function(settings){
		$(settings.formId).on('submit', function(event) {
            var form = this;
            event.preventDefault();

            // Validate Customer
            if (!self.validateCustomer(settings.inputClass)) {
                // Show some kind of error here
                $('#customer_error_headline').html("Wrong information");
                $('#customer_error_message').html("Please fill in correct information");
                $("#customer_error").show();

                return;
            }
            $("#customer_error").hide();

            var exp = $(settings.expiryField).val().replace(/\s/g, '').split("/");

            // alternatively..
            var paymentinfo = {
                // TODO: the name must be cardnumber not only number. Server supports number fix.
                number: $(settings.cardnumberField).val(),
                month: exp[0],
                year: exp[1],
                cvv: $(settings.cvvField).val()
            };
            console.log(paymentinfo);
            reepay.validate.cardNumber(paymentinfo['number']);

            var $btn = $('#savebutton').button('loading');

            if (!$(settings.termsId)[0].checked) {
                // This should be changed to suit your needs and your HTML
                $("#terms-group").addClass("has-error");

                $('#error_headline').html("Accept terms");
                $('#error_message').html("Remember to accept terms");
                $('#error').show();

                $btn.button('reset');
                return;
            }

            reepay.token(paymentinfo, function(err, token) {
                console.log("Token: " + token);
                // Handle token error and success here. This is just an example.
                // Should be changed to suit your needs and your HTML.
                if (err) {
                    console.log("An error happened: code: " + err.code + " message: " + err.message);
                    var errText = interpretError(err, settings.locale);
                    var headline = errText.headline;
                    var message = errText.message;

                    $('#error_headline').html(headline);
                    if (err.fields) {
                        message = message + err.fields;
                    }
                    $('#error_message').html(message);
                    $('#error').show();
                    $btn.button('reset');
                } else {
                    $("[data-reepay=token]").val(token.id);
                    	form.submit();
                }
            });
		});
	};



	// Public to call
	return {
		// Validates input with the given settings in the constructor
		validate: function(){
			self.validate(self.validationSettings);
		},
		reepaySubmit: function(){
			self.submitForm(self.validationSettings);
		}
	}
}