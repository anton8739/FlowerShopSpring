var dropArea = document.getElementById('drop-area');

['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
    dropArea.addEventListener(eventName, preventDefaults, false);
});
function preventDefaults (e) {
    e.preventDefault()
    e.stopPropagation()
}
['dragenter', 'dragover'].forEach(eventName => {
    dropArea.addEventListener(eventName, highlight, false);
});
['dragleave', 'drop'].forEach(eventName => {
    dropArea.addEventListener(eventName, unhighlight, false);
});
function highlight(e) {
    dropArea.classList.add('highlight');
}
function unhighlight(e) {
    dropArea.classList.remove('highlight');
}

dropArea.addEventListener('drop', handleDrop, false);
function handleDrop(e) {
    var dt = e.dataTransfer;
    var files = dt.files;
    handleFiles(files);
}

function handleFiles(files) {
    var preview=document.getElementById("preview");
    preview.innerHTML = "";
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        if (!file.type.startsWith('image/')){ continue }

        var img = document.createElement("span");


        img.classList.add("miniImg");
        img.innerHTML = "<i class=\"fa fa-picture-o\" aria-hidden=\"true\"></i>&nbsp;"+file.name+"&emsp;";
        preview.appendChild(img);

    }

    document.getElementById('fileElem').files=files;
}

function sendFile(){
    var selectedFile = document.getElementById('fileElem').files;
    var selectedFileText=" ";
    for (var i=0; i < selectedFile.length; i++){
        selectedFileText+=selectedFile[i].name+" ";
        console.log(selectedFile[i]);
    }
    console.log(selectedFileText);
}