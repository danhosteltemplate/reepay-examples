var reepay_util = {};

reepay_util.validation = {
	settings: {
		checkEmail: true,

	}
}

reepay_util.form = {
	settings: {
		fields: {
			firstName: true, 
			lastName: true, 
			email: true,
			username: false,
			password: false,
			firm: false,
			address: true,
			address2: true,
			zipcode: true,
			city: true
		}
	}
}

reepay_util.validateInput = function(inputName){
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
// Validate Customer info
function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}



function validateCustomer() {

    var status = true;

    $(".customer_info").each(function() {

        if ($(this).attr('required')) {
            if ($(this).val() == "") {
                console.log($(this).attr("name") + " is empty");
                status = false;
                return;
            }
        }

        if ($(this).attr("name") == "email" && !isEmail($(this).val())) {
            console.log($(this).attr("name") + " is not valid");
            status = false;
            return;
        }
    });
    return status;
}

reepay_util.validate = function(inputClass){
	reepay_util.validateInput(inputClass);

	if(reepay_util.validation.settings.checkEmail){
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
	validateCustomer();
}

reepay_util.generate = function(args) {
	$.extend(reepay_util.form.settings.fields, args);
	console.log(this);
}

