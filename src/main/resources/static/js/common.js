		/*
		* Form 데이터를 json형태로 보낼때 변경이 필요함
		* 사용법 : var post_to_json = $("#tform").serializeObject();
		*/
		jQuery.fn.serializeObject = function() {
		    var obj = null;
		    try {
		        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
		            var arr = this.serializeArray();
		            if (arr) {
		                obj = {};
		                jQuery.each(arr, function() {
		                    obj[this.name] = this.value;
		                });
		            }//if ( arr ) {
		        }
		    } catch (e) {
		        alert(e.message);
		        console.log(e);
		    } finally {
		    }
		 
		    return obj;
		}; // End
		
		$(document).ready(function() {
			console.log("common.js script log >>> ");
		}); // End 		