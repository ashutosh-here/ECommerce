//$(window).on('load', function () {
//    // setTimeout( $('#mainC').show(),3000);   
//    // $('#mainC').show();
//    setTimeout(function () {
//        $('#nloader').hide();
//        $('#mainC').show();
//    }, 800)
//    // $('#mainC').show();
//
////    setTimeout(function (){
////      $('#mainC').show();
////        
////    },100)
//    //  $('#mainC').show();
//    //  $('#nloader').hide();
//
//});




function showModal(id,title,description)
{
// alert(id);
// alert(title);
// alert(description);
 

 $("#catId").val(id) ;// = id;
 $("#catT").val(title) ;// = id;
 $("#catD").val(description) ;
     
 
}

function deleteCat(data){
    //alert(data);
    console.log(data);
    
    swal({
  title: "Are you sure?",
  text: "Once deleted, you will not be able to recover this imaginary file!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {
  if (willDelete) {
      
      
   
              let id=data;      
                    
               console.log("before deleting id:"+id)  ;   
              
            
            
            $.post( "DeleteCategoryServlet", {"catID":id},function( data ) {
             
            
                console.log(data);
                if (data.trim() == 'ss')
                {
//                    swal("Done!", "Succesfully updated", "success");
                 swal("Done!", "Succesfully deleted", "success"); 
                    
                    location.reload();
                
                
                }
                
            else
                {
                    swal("Error!!", "Something went wrong try again...", "error");
                }

            
            
            
            
                    });
            
            
            
//    $.ajax({
//            url: "DeleteCategoryServlet",
//            type: 'POST',
//            data: id ,          
//            dataType: "text",
//            crossDomain: true,
//          //  headers: {"Access-Control-Allow-Origin" : "*" , "Access-Control-Allow-Methods" : "POST, GET"},
//            success: function (data, textStatus, jqXHR) {
//                
//               
//                   console.log(data);
//                if (data.trim() == 'ss')
//                {
////                    swal("Done!", "Succesfully updated", "success");
//                 swal("Done!", "Succesfully deleted", "success"); 
//                    
//                    location.reload();
//                
//                
//                }
//                
//            else
//                {
//                    swal("Error!!", "Something went wrong try again...", "error");
//                }
//
//          
//            },
//
//            error: function (jqXHR, textStatus, errorThrown) {
//                //error..
//                swal("Error!!", "Something went wrong try again...", "error");
//            }
//
//
//        });
                    
                    
                    
                    
                    
                    
                    
                    
                    
      
      
   
  } 
});
    
    
    
    
    
    
}





$(document).ready(function () {







//seaerch bar
  $("#myInput").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myTable tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });






$('#UpdateCategoryForm').submit(function (event) {
        event.preventDefault(); // Prevent the form from submitting via the browser
           console.log("clicked on submit");
        let form = $(this);
           console.log(form);


  $.ajax({
            url: "UpdateCategoryServlet",
            type: 'POST',
            data: form.serialize() ,          
            dataType: "text",
            crossDomain: true,
          //  headers: {"Access-Control-Allow-Origin" : "*" , "Access-Control-Allow-Methods" : "POST, GET"},
            success: function (data, textStatus, jqXHR) {
                
               
                   console.log(data);
                if (data.trim() == 'ss')
                {
//                    swal("Done!", "Succesfully updated", "success");
                 swal("Done!", "Succesfully updated", "success"); 
                    
                    location.reload();
                
                
                }
                
            else
                {
                    swal("Error!!", "Something went wrong try again...", "error");
                }

          
            },

            error: function (jqXHR, textStatus, errorThrown) {
                //error..
                swal("Error!!", "Something went wrong try again...", "error");
            }


        });
        
               });



































    $.fn.rowCount = function () {
        return $('tr', $(this).find('tbody')).length;
    };

    $.fn.columnCount = function () {
        return $('th', $(this).find('tbody')).length;
    };
    var
            rowctr = $('#table').rowCount();
    var colctr = $('#table').columnCount();


    $('.h2').append(rowctr);
    console.log('No of Rows:' + rowctr);
    console.log('No of Columns:' + colctr);






    var table = $('#table');

    // Table bordered
    $('#table-bordered').change(function () {
        var value = $(this).val();
        table.removeClass('table-bordered').addClass(value);
    });

    // Table striped
    $('#table-striped').change(function () {
        var value = $(this).val();
        table.removeClass('table-striped').addClass(value);
    });

    // Table hover
    $('#table-hover').change(function () {
        var value = $(this).val();
        table.removeClass('table-hover').addClass(value);
    });

    // Table color
    $('#table-color').change(function () {
        var value = $(this).val();
        table.removeClass(/^table-mc-/).addClass(value);
    });
    
    
});


(function (removeClass) {

    jQuery.fn.removeClass = function (value) {
        if (value && typeof value.test === "function") {
            for (var i = 0, l = this.length; i < l; i++) {
                var elem = this[i];
                if (elem.nodeType === 1 && elem.className) {
                    var classNames = elem.className.split(/\s+/);

                    for (var n = classNames.length; n--; ) {
                        if (value.test(classNames[n])) {
                            classNames.splice(n, 1);
                        }
                    }
                    elem.className = jQuery.trim(classNames.join(" "));
                }
            }
        } else {
            removeClass.call(this, value);
        }
        return this;
    }

})(jQuery.fn.removeClass);