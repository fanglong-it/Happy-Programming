//CounterUp
jQuery(document).ready(function( $ ) {
  $('.counter').counterUp({
    delay: 1,
    time: 800
  });
});


// Page Loader
$(window).load(function() {
  "use strict";
  $('#loading').fadeOut();
});

 /* Slicknav Mobile Menu
========================================================*/
  $(document).ready(function(){
    $('.wpb-mobile-menu').slicknav({
      prependTo: '.navbar-header',
      parentTag: 'jobs',
      allowParentLinks: true,
      duplicate: true,
      label: '',
      closedSymbol: '<i class="fa fa-angle-right"></i>',
      openedSymbol: '<i class="fa fa-angle-down"></i>',
    });
  });

 // Nav Menu & Search
  $(".nav > li:has(ul)").addClass("drop");
  $(".nav > li.drop > ul").addClass("dropdown");
  $(".nav > li.drop > ul.dropdown ul").addClass("sup-dropdown");

/* ==========================================================================
   Revolution Slider
   ========================================================================== */
  $(document).ready(function() {
     jQuery('.tp-banner').show().revolution({
      dottedOverlay: "none",
      delay: 9000,
      startwidth: 1170,
      startheight: 540,
      hideThumbs: 200,
      thumbWidth: 100,
      thumbHeight: 50,
      thumbAmount: 5,
      navigationType: "bullet",
      navigationArrows: "solo",
      navigationStyle: "preview3",
      touchenabled: "on",
      onHoverStop: "on",
      swipe_velocity: 0.7,
      swipe_min_touches: 1,
      swipe_max_touches: 1,
      drag_block_vertical: false,
      parallax: "mouse",
      parallaxBgFreeze: "on",
      parallaxLevels: [7, 4, 3, 2, 5, 4, 3, 2, 1, 0],
      keyboardNavigation: "off",
      navigationHAlign: "center",
      navigationVAlign: "bottom",
      navigationHOffset: 0,
      navigationVOffset: 20,
      soloArrowLeftHalign: "left",
      soloArrowLeftValign: "center",
      soloArrowLeftHOffset: 20,
      soloArrowLeftVOffset: 0,
      soloArrowRightHalign: "right",
      soloArrowRightValign: "center",
      soloArrowRightHOffset: 20,
      soloArrowRightVOffset: 0,
      shadow: 0,
      fullWidth: "on",
      fullScreen: "off",
      spinner: "spinner1",
      stopLoop: "off",
      stopAfterLoops: -1,
      stopAtSlide: -1,
      shuffle: "off",
      autoHeight: "off",
      forceFullWidth: "off",
      hideThumbsOnMobile: "off",
      hideNavDelayOnMobile: 1500,
      hideBulletsOnMobile: "off",
      hideArrowsOnMobile: "off",
      hideThumbsUnderResolution: 0,
      hideSliderAtLimit: 0,
      hideCaptionAtLimit: 0,
      hideAllCaptionAtLilmit: 0,
      startWithSlide: 0,
      fullScreenOffsetContainer: ""
      });
    });

/* ==========================================================================
   Touch Owl Carousel
   ========================================================================== */
$(".touch-slider").owlCarousel({
    navigation: true,
    pagination: false,
    slideSpeed: 1000,
    stopOnHover: true,
    autoPlay: true,
    items: 1,
    itemsDesktopSmall: [1024, 1],
    itemsTablet: [600, 1],
    itemsMobile: [479, 1]
});

$('.touch-slider').find('.owl-prev').html('<i class="ti-angle-left"></i>');
$('.touch-slider').find('.owl-next').html('<i class="ti-angle-right"></i>');

$('#new-products').find('.owl-prev').html('<i class="fa fa-angle-left"></i>');
$('#new-products').find('.owl-next').html('<i class="fa fa-angle-right"></i>');

var owl;

$(document).ready(function () {

    owl = $("#owl-demo");

    owl.owlCarousel({

        navigation: false, // Show next and prev buttons
        slideSpeed: 300,
        paginationSpeed: 400,
        singleItem: true,
        afterInit: afterOWLinit, // do some work after OWL init
        afterUpdate : afterOWLinit
    });

    function afterOWLinit() {
        // adding A to div.owl-page
        $('.owl-controls .owl-page').append('<a class="item-link" />');
        var pafinatorsLink = $('.owl-controls .item-link');
        /**
         * this.owl.userItems - it's your HTML <div class="item"><img src="http://www.ow...t of us"></div>
         */
        $.each(this.owl.userItems, function (i) {

            $(pafinatorsLink[i])
                // i - counter
                // Give some styles and set background image for pagination item
                .css({
                    'background': 'url(' + $(this).find('img').attr('src') + ') center center no-repeat',
                    '-webkit-background-size': 'cover',
                    '-moz-background-size': 'cover',
                    '-o-background-size': 'cover',
                    'background-size': 'cover'
                })
                // set Custom Event for pagination item
                .click(function () {
                    owl.trigger('owl.goTo', i);
                });

        });
         // add Custom PREV NEXT controls
        $('.owl-pagination').prepend('<a href="#prev" class="prev-owl"/>');
        $('.owl-pagination').append('<a href="#next" class="next-owl"/>');
        // set Custom event for NEXT custom control
        $(".next-owl").click(function () {
            owl.trigger('owl.next');
        });
        // set Custom event for PREV custom control
        $(".prev-owl").click(function () {
            owl.trigger('owl.prev');
        });
    }
});

//testimonial Slider
  $('#testimonial-carousel').owlCarousel({   
      navigation: false,
      pagination: true,
      slideSpeed: 1000,
      autoPlay: false,
      singleItem: true,
      pagination : false,
      items : 1,
      itemsDesktop : [1199,4],
      itemsDesktopSmall : [980,3],
      itemsTablet: [768,2],
      itemsTabletSmall: false,
      itemsMobile : [479,1],
  });


/* Owl Carousel
========================================================*/
$('#clients-scroller').owlCarousel({
    navigation: false,
    pagination: false,
    items:6,
    itemsTablet:3,
    stagePadding:90,
    smartSpeed:450,
    itemsDesktop : [1199,4],
    itemsDesktopSmall : [980,3],
    itemsTablet: [768,3],
    itemsTablet: [767,2],
    itemsTabletSmall: [480,2],
    itemsMobile : [479,1],
});

 // Back Top Link
  var offset = 200;
  var duration = 500;
  $(window).scroll(function() {
    if ($(this).scrollTop() > offset) {
      $('.back-to-top').fadeIn(400);
    } else {
      $('.back-to-top').fadeOut(400);
    }
  });
  $('.back-to-top').click(function(event) {
    event.preventDefault();
    $('html, body').animate({
      scrollTop: 0
    }, 600);
    return false;
  })

 $('.list,switchToGrid').click(function(e) {
    e.preventDefault();
    $('.grid').removeClass("active");
    $('.list').addClass("active");
    $('.item-list').addClass("make-list");
    $('.item-list').removeClass("make-grid");
    $('.item-list').removeClass("make-compact");
    $('.item-list .add-desc-box').removeClass("col-sm-9");
    $('.item-list .add-desc-box').addClass("col-sm-7");
  });
  $('.grid').click(function(e) {
    e.preventDefault();
    $('.list').removeClass("active");
    $(this).addClass("active");
    $('.item-list').addClass("make-grid");
    $('.item-list').removeClass("make-list");
    $('.item-list').removeClass("make-compact");
    $('.item-list .add-desc-box').removeClass("col-sm-9");
    $('.item-list .add-desc-box').addClass("col-sm-7");
  });

  jQuery(document).ready(function($){
  var $form_modal = $('.cd-user-modal'),
    $form_login = $form_modal.find('#cd-login'),
    $form_signup = $form_modal.find('#cd-signup'),
    $form_forgot_password = $form_modal.find('#cd-reset-password'),
    $form_modal_tab = $('.cd-switcher'),
    $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
    $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
    $forgot_password_link = $form_login.find('.cd-form-bottom-message a'),
    $back_to_login_link = $form_forgot_password.find('.cd-form-bottom-message a');


  //switch from a tab to another
  $form_modal_tab.on('click', function(event) {
    event.preventDefault();
    ( $(event.target).is( $tab_login ) ) ? login_selected() : signup_selected();
  });

  //show forgot-password form 
  $forgot_password_link.on('click', function(event){
    event.preventDefault();
    forgot_password_selected();
  });

  //back to login from the forgot-password form
  $back_to_login_link.on('click', function(event){
    event.preventDefault();
    login_selected();
  });

  function login_selected(){
    $form_login.addClass('is-selected');
    $form_signup.removeClass('is-selected');
    $form_forgot_password.removeClass('is-selected');
    $tab_login.addClass('selected');
    $tab_signup.removeClass('selected');
  }

  function signup_selected(){
    $form_login.removeClass('is-selected');
    $form_signup.addClass('is-selected');
    $form_forgot_password.removeClass('is-selected');
    $tab_login.removeClass('selected');
    $tab_signup.addClass('selected');
  }

  function forgot_password_selected(){
    $form_login.removeClass('is-selected');
    $form_signup.removeClass('is-selected');
    $form_forgot_password.addClass('is-selected');
  }

});
window.setTimeout(function () {
    $(".alert").fadeTo(500, 0).slideUp(500, function () {
        $(this).remove();
    });
}, 10000);

function checkEmailForRegister() {
    var email = document.getElementById('email-check').value;

    jQuery.ajax({
        url: "/check_email",
        type: "GET",
        data: {email: email},
        success: function (result) {
            console.log(result);
            $("#message-1").html(result);
        }, error: function () {
            $("#message-1").html("");
        }
    });
    if (document.getElementById('email-check').value == "") {
        document.getElementById('message-1').style.display = "none";
    } else
        document.getElementById('message-1').style.display = "block";
}

function checkEmailForChangePass() {
    var email = document.getElementById('email-change-password').value;
    document.getElementById('message-2').innerHTML="Processing.............";
    jQuery.ajax({
        url: "/reset-password",
        type: "POST",
        data: {email: email},
        success: function (result) {
            $("#message-2").html(result);
        }, error: function () {
            console.log("aosdoaihsdoiashod")
            $("#message-2").html("");
        }
    });
    if (document.getElementById('email-change-password').value == "") {
        document.getElementById('message-2').style.display = "none";
        document.getElementById('btn-change-password').disable = "true";
    } else
        document.getElementById('message-2').style.display = "block";
}

function deliveredTimeVerify(){
    let time = document.getElementById('deliveryTime').value;
    console.log(time);
    if(isNaN(time) || time == ""){
        document.getElementById('deliveryTime').value = "";
    }else{
        document.getElementById('deliveryTime').value = time+" Days";
    }
}


function budgetVerify(){
    let budget = document.getElementById('budget').value;
    if(isNaN(budget) || budget == ""){
        document.getElementById('budget').value = "";
    }else{
        document.getElementById('budget').value = budget+"$";
    }
}

var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function displayRate(id){
    console.log(id);
    modal.style.display = "block";
    jQuery.ajax({
        url: "/get-rate-comment",
        type: "GET",
        data: {mentorId:id},
        success: function (result) {
            $("#rate-comment").html(result);
        }, error: function () {
            $("#message-2").html("");
        }
    });
    document.getElementById('mentor-id').value = id;

}
function hideRate(){
        modal.style.display = "none";
}

function saveReview(){
    var id = document.getElementById('rate-comment-id').value;
    var comment = document.getElementById('new-comment').value;
    var selectedVal = "";
    var selected = $("input[type='radio'][name='star']:checked");
    if (selected.length > 0) {
        selectedVal = selected.val();
    }
    console.log(selectedVal);
    jQuery.ajax({
        url: "/save-rate-comment",
        type: "POST",
        data: {rateCommentId:id,
                comment: comment,
                starNumber: selectedVal},
        success: function () {

        }, error: function () {

        }
    });
    modal.style.display = "none";

}
function loadData(){
    jQuery.ajax({
        url: "/get-uncheck-notification",
        type: "GET",
        success: function (result) {
            if(result=="0"){
                document.getElementById('noti1').innerHTML="";
            }else{
                document.getElementById('noti1').innerHTML=result;
            }
        }, error: function () {
        }
    });
}
function displayNotification(){
    if(document.getElementById('notification').style.display=="none"){
        document.getElementById('notification').style.display="block";
        document.getElementById('noti1').innerHTML="";
        if(document.getElementById('fill-content').innerHTML==""){
            console.log("get data ...........................")
            console.log("get data")
            jQuery.ajax({
                url: "/get-notification-content",
                type: "GET",
                success: function (result) {
                    $('#fill-content').html(result);
                    console.log(result)
                }, error: function () {
                }
            });
        }
    }else{
        document.getElementById('notification').style.display="none";
    }
}

function  updateProfile(){
    var fullName = document.getElementById('fullName').value;
    var DoB = document.getElementById('DoB').value;
    var phone = document.getElementById('phone').value;
    jQuery.ajax({
        url: "/update-profile",
        type: "post",
        data: {
            fullName: fullName,
            DoB: DoB,
            phone: phone,
        },
        success: function (result) {
        }, error: function () {
        }
    });
    modal.style.display = "none";
    
    function f() {
        
    }
}
function isValidDate(s) {
    // Assumes s is "dd/mm/yyyy"

    if ( ! /^\d{1,2}\/\d{1,2}\/\d{4}$/.test(s) ) {
        return false;
    }
    const parts = s.split('/').map((p) => parseInt(p, 10));
    parts[1] -= 1;
    const d = new Date(parts[2],parts[1], parts[0]);
    return d.getMonth() === parts[1] && d.getDate() === parts[0] && d.getFullYear() === parts[2];
}
function isValidPhone(s) {
    // Assumes s is "dd/mm/yyyy"

    if ( ! /^\d{9, 11}$/.test(s) ) {
        return false;
    }
    return truel;
}

function testValidDate() {
    let s = document.getElementById('dob').value;
    console.log("dob: "+s);
    if (isValidDate(s) == false) document.getElementById('dob').value = "";
    else document.getElementById('dob').value =s;
}


function testValidInputCV() {
    var name = document.getElementById('mentorName')==null? null : document.getElementById('mentorName').value;
    var dob = document.getElementById('dob')==null? null :document.getElementById('dob').value;
    var title = document.getElementById('title')==null? null: document.getElementById('title').value;
    var title1 = document.getElementById('title1')==null? null: document.getElementById('title1').value;
    var profession = document.getElementById('profession')==null? null : document.getElementById('profession').value;
    var profession1 = document.getElementById('profession1')==null? null :document.getElementById('profession1').value;
    var intro = document.getElementById('introduction')==null? null: document.getElementById('introduction').value;
    var intro1 = document.getElementById('introduction1')==null? null: document.getElementById('introduction1').value;
    var checkboxs=document.getElementsByName("skills");
    var  okay = false;
    for(var i=0,l=checkboxs.length;i<l;i++)
    {
        if(checkboxs[i].checked){
            okay=true;
            break;
        }
    }
    if (!okay) return false;

    if ((title==""||title==null) && (title1=="" || title1== null)){
        console.log("title");
        return false;
    }
    else if ((profession == ""|| profession == null) && (profession1 == "" ||  profession1 == null)) {
        console.log("pro")
        return false;
    }
    else if ((intro == "" || intro == null) && (intro1 == "" || intro1 == null)) {
        console.log("intro")
        return false;
    }    else if (dob== null || dob == ""){
        console.log("dob");
        return false;
    }
    else if (name == "" || name == null )  {
        console.log("name" + name + "....");
        return false;
    }
    return  true;
}
function testValidCV() {
    if (testValidInputCV()==false) {
        alert("There are some empty fields. Please fill them out.");
        return false;
    }
}



