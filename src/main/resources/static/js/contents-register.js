editor.addHook("addImageBlobHook", function (blob, callback) {
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
        },
        error: function () {
            callback('image_upload_fail', 'Image')
        }
    })
})

const fileSelected = document.getElementById("file-select")
fileSelected.addEventListener('change', getImageFiles);

function getImageFiles(e) {
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
        },
        error: function () {
            fileUrl.value = 'Image Upload Failed!';
        }
    })
}

