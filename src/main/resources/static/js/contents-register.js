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