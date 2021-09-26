
function copyToClipboard(element, textData, data) {
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val($(element).text()).select();
    document.execCommand("copy");
    // console.log(data)
    // navigator.clipboard.readText().then(clipText =>
    //     document.getElementById("outbox").innerText = clipText);
    //
    // var pasteText = document.querySelector("#output");
    // pasteText.focus();
    // document.execCommand("paste");
    // console.log(pasteText.textContent);
    document.getElementById("output").innerHTML=textData;
    document.getElementById("outputModal").innerHTML=data;
    document.getElementById("outputModal").href='tel:'+data;

    // $("#output").find("p").html("asd");
    $temp.remove();
};

$('.custom-file-input').on('change', function() {
    let fileName = $(this).val().split('\\').pop();
    $(this).siblings('.custom-file-label').addClass('selected').html(fileName);
});

var app = document.getElementById('app');

var typewriter = new Typewriter(app, {
    loop: true
});

typewriter.typeString('Unmochon')
    .pauseFor(2500)
    .deleteAll()
    .typeString('A Tool to Combat Online Sexual Harassment')
    .pauseFor(2500)
    .start();

//
// typewriter.typeString('HCI Bangladesh')
//     .pauseFor(2500)
//     .deleteAll()
//     .typeString('We will change the direction of technology together.')
//     .pauseFor(2500)
//     .deleteChars(7)
//     .typeString('<strong>altered!</strong>')
//     .pauseFor(2500)
//     .start();
// MDB Lightbox Init
$(function () {
    $("#mdb-lightbox-ui").load("mdb-addons/mdb-lightbox-ui.html");
});



// Get the modal
var modal = document.getElementById("myModal");

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById("myImg");
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
