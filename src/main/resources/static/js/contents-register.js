editor.addHook("addImageBlobHook", function (blob, callback) {
    showSpinner();
    const formData = new FormData();
    formData.append('file', blob);

    $jLatest.ajax({
        url: "/file/upload",
        type: "PUT",
        async: true,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        data: formData,
        success: function (response) {
            callback(response, 'Image')
            hideSpinner();
        },
        error: function () {
            callback('image_upload_fail', 'Image')
            hideSpinner();
        },

    })
})

const fileSelected = document.getElementById("file-select")
fileSelected.addEventListener('change', getImageFiles);

function getImageFiles(e) {
    showSpinner();
    const files = e.currentTarget.files;
    console.log(typeof files, files);
    const fileUrl = document.getElementById("thumbnail");
    const formData = new FormData();
    formData.append('file', files[0]);

    $jLatest.ajax({
        url: "/file/upload",
        type: "PUT",
        async: true,
        contentType: false,
        processData: false,
        enctype: 'multipart/form-data',
        data: formData,
        success: function (response) {
            fileUrl.value = response;
            hideSpinner();
        },
        error: function () {
            fileUrl.value = 'Image Upload Failed!';
            hideSpinner();
        }
    })
}

function showSpinner() {
    document.getElementsByClassName('layerPopup')[0].style.display='block';
}

function hideSpinner() {
    document.getElementsByClassName('layerPopup')[0].style.display='none';
}