<link rel="stylesheet" href="css/agile_carousel.css" type='text/css'>
<div class="slideshow" id="multiple_slides_visible"></div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.0/jquery.js"></script>
<script src="http://www.agilecarousel.com/agile_carousel/agile_carousel.a1.1.min.js"></script>

<script>


var data = [
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas1.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas2.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas3.jpg' alt='Tunnel'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas4.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/nike1.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
	    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/nike2.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas1.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas2.jpg' alt='Tunnel'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas3.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas4.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
	    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/nike1.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/nike2.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas1.jpg' alt='Tunnel'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas2.jpg' alt='Bike'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    },
    {
        "content": "<div class='slide_inner'><a target='_blank' class='photo_link' href='#'><img class='photo' src='images/adidas3.jpg' alt='Paint'></a><a target='_blank' class='caption' href='#'>Buy it Now for $ 2.49 at amazon.com</a></div>",
        "content_button": "<div class='thumb'><img src='images/f2_thumb.jpg' alt='bike is nice'></div><p>Agile Carousel Place Holder</p>"
    }
];

$.getJSON("http://localhost:8080/RecommendationSystem/recommendations", function(result){
    data = results;
 });



        $(document).ready(function(){
            $("#multiple_slides_visible").agile_carousel({
                carousel_data: data,
                carousel_outer_height: 230,
                carousel_height: 200,
                slide_height: 200,
                carousel_outer_width: 480,
                slide_width: 160,
                number_slides_visible: 5,
                transition_time: 330,
                control_set_1: "previous_button,next_button",
                control_set_2: "group_numbered_buttons",
                persistent_content: "<p class='persistent_content'>Recommended For You</p>"       
            });
        });
</script>


 

