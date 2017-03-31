function reepay_utils(valSettings){
	var self = this;
	// Settings which can extend/overwrite defaults by parsing an object as a parameter
	// Everything but the parameter object and the functions in the return block is private
	// 
	self.validationSettings = {
		cvvField: "",
		cardnumberField: "",
		expiryField: "",
		inputClass: "",
		checkEmail: true,
	};
	$.extend( validationSettings, valSettings );

	// Validating tha all required input fields are filled out
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

	//Check if the email fits the standard emai format
	self.isEmail = function(email) {
	    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	    return regex.test(email);
	}

	//Validate that the customer info fields has been filled out
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
            if (!reepay.validate.cardNumber($('#cardnumber').val())) {
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

	// The public validate function
	self.validate = function(settings){
		self.validateInput(settings.inputClass);

		if(settings.checkEmail){
			$("input[name=email]").blur(function() {
			    if (!isEmail($(this).val())) {
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
	}



	// Public to call
	return {
		//Validates input with the given settings in the constructor
		validate: function(){
			self.validate(validationSettings);
		}
	}
}



function test() {

}


