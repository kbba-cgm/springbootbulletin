$(document).ready(function() {
    const default_preview_photo = 'https://static.thenounproject.com/png/1768486-200.png';

    if($("#photo_handler").val().length > 0) {
        $("#remove-btn").removeClass("d-none");
    }

    const prepareImage = fileLists => {
        const reader = new FileReader();
		reader.onload = (e) => {
            $("#photo_handler").val(e.target.result);
            $("#preview-photo").attr("src", e.target.result);
        }
	    reader.readAsDataURL(fileLists[0]);
    };

    const cleanImageData = () => {
		$("#uploadPhoto").val('');
		$("#preview-photo").attr("src", default_preview_photo);
		$("#photo_handler").val('');
		//if($('#oldImageString')) $('#oldImageString').val('');
		$("#remove-btn").addClass("d-none");
	}

    $("#uploadPhoto").change(function(){
        if(!this.files || !this.files[0]) {
			cleanImageData();
			return false;
		}else if (!this.files[0].type.startsWith('image/')) {
			cleanImageData();
			$("#photo-type-error").text("File type must be image.");
			return false;		
		}
		
		prepareImage(this.files);
		$("#remove-btn").removeClass("d-none");
		$("#profile-photo-type-error").text("");
		//if($('#oldImageString')) $('#oldImageString').val('');
    })

    $("#remove-btn").click(() => cleanImageData());
})