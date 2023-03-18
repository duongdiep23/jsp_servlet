function checkValidSName(){
	let sname = document.getElementById("sname").value;
	
	var validsname = true;
	
	var message ="";
	
	let errSName = document.getElementById("errValue");
	
	sname = sname.trim();
	
	if(sname != null){
		
		if(sname != ""){
			validsname = true;
		}else{
			message = "Tên chuyên mục không được để trống !!";
			validsname = false;
		}
		
	} else{
		message = "Lỗi";
		validsname = false;
	}
	
	if(!validsname){
		errSName.innerHTML = message;
	}else{
		
	}
	
	return validsname;
}

function createSection(fn){
	if(this.checkValidSName()){
		fn.method = "Post";
		fn.action = "/adv/section/add";
		fn.submit();
	}
}