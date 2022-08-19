"use strict";

var slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) { showSlides(slideIndex += n) }

// Thumbnail image controls
function currentSlide(n) { showSlides(slideIndex = n) }

var slideIndex = 0;
showSlides();

function showSlides() {
    var slides = document.getElementsByClassName("mySlides");

    for (var i = 0; i < slides.length; i++) {
        slides[i].style.display = "none"
    }
    slideIndex++;
    if (slideIndex > slides.length) { slideIndex = 1 }
    slides[slideIndex - 1].style.display = "block";
    setTimeout(showSlides, 5000); // Change image every 5 seconds
}