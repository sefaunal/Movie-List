var uploadField = document.getElementById("movieImageFile");

uploadField.onchange = function() {
    if(this.files[0].size > 1048576){
       alert("File is Too Big! (Max Size is 1MB)");
       this.value = "";
    }else{
    var fileName = uploadField.value;
    var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="jpg" || extFile=="jpeg" || extFile=="png"){
            //TO DO
        }else{
            alert("Only JPG/JPEG & PNG Files Are Allowed!");
            this.value="";
        }
    }
};