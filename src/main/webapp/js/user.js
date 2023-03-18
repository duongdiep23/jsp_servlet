//xử lý kịch bản cho user.html

function generatePermis(){
	//khai bao quyen thuc thi trong he thong
	var permis = new Array();
	permis[0] = "---";
	permis[1] = "Members";
	permis[2] = "Authors";
	permis[3] = "Managers";
	permis[4] = "Administrator";
	permis[5] = "Super Admin";
	
	var opt = '<select class="form-control" id="permis" name="slcUserPermis">';
	for (var i=0; i<permis.length; i++){
		opt += '<option value="'+i+'">';
		opt += permis[i];
		opt += '</option>';
	
	}
	opt += '</select>';
	
	//in ra mh
	document.write(opt);
}

function generateRoles(){
	var roles = new Array();
	roles[0]= "User";
	roles[1]= "Section";
	roles[2]= "Category";
	roles[3]= "Article";
	roles[4]= "Product System";
	roles[5]= "Product Group";
	roles[6]= "Product Category";
	roles[7]= "Product";
	roles[8]= "Order";
	roles[9]= "Customer";
	roles[10]= "Log";
	
	let role = "";
	for(var i=0; i<roles.length; i++){
		//mở dòng
		if(i%3==0){
			role += '<div class="row my-2 align-items-center">';
		}
		role +='<div class="col-md-4">';
		role +='<i class="fa-solid fa-user"></i> &nbsp;'; 
		role +='<input type="checkbox" class="form-check-input" id="chk'+i+'" disabled name="chks" onclick = "checkPermis()"> &nbsp;'; 
		role +='<label class="form-check-label" for="chk'+i+'">'+roles[i]+' management</label>';
		role +='</div>';
		
		
		//đóng dòng
		if((i%3==2) || (i==roles.length-1)){
			role += '</div>';
		}
	}
	
	document.write(role);
}

function setCheckBox(fn, check, dis){
	//duyet cac phan tu cua form
	for(let i =0; i<fn.elements.length; i++){
		if((fn.elements[i].type == "checkbox") && (fn.elements[i].name == "chks")){
			fn.elements[i].checked = check;
			fn.elements[i].disabled = dis;
		}
	}
}

function refreshPermis(fn){
	//lay permis
	var permis = parseInt(document.getElementById("permis").value);
	if(permis == 4 || permis == 5){
		this.setCheckBox(fn, true, true);
	}else if(permis==3){
		this.setCheckBox(fn, true, false);
	}else if(permis==2){
		this.setCheckBox(fn, false, false);
	}else{
		this.setCheckBox(fn, false, true);
	}
	
	this.checkPermis();
}

function checkUsername(){
	//lay thong tin
	let name = document.getElementById("name").value;
	
	var validUserName = true;
	
	var message = "";
	
	//tham chieu doi tg hien thi loi
	let errName = document.getElementById("errUsername");
	//tham chiếu đối tượng nhập email
	let email = document.getElementById("email");
	
	if(name.trim()== ""){
		validUserName = false;
		message = "Thiếu tên hoặc hộp thư đăng nhập vào hệ thống";
	}else{
		if((name.length<5)|| (name.length>50)){
			validUserName = false;
			message = "Tên đăng nhập nên có độ dài trong khoảng 5 - 50 ký tự"
		}else{
			if(name.indexOf(" ")!= -1){
				validUserName = false;
				message = "Tên đăng nhập có dấu cách";
			}else if(name.indexOf("@")!= -1){
				var parttern = /\w+@\w+[.]\w/;
				if(!name.match(parttern)){
					validUserName = false;
					message = "Không đúng cấu trúc hộp thư";
					email.disabled = false;
				}else{
					email.disabled = true;
					document.getElementById('errEmail').innerHTML = "";
					document.getElementById('errEmail').style.backgroudColor= "transpatent";
				}
			}else{
				email.disabled = false;
				
			}
		}
	}
	
	//Thông báo
	if(!validUserName){
		errName.innerHTML = message;
		errName.style.color = "red";
	}else{
		errName.innerHTML ='<i class="fa-solid fa-check"></i>';
		errName.style.color = "blue";
	}
	
	return validUserName;
}

function checkUserpass(){
	let pass1 = document.getElementById('pass').value;
	let pass2 = document.getElementById('pass2').value;
	
	var validUserPass = true;
	
	var message = "";
	
	//tham chieeus doi tuong bao loi
	let errPass = document.getElementById('errPass');
	
	//ktra
	pass1 = pass1.trim();
	if(pass1 == ""){
		validUserPass = false;
		message = "Nhập mật khẩu cho tài khoản";
	}else{
		if(pass1.length<6){
			validUserPass = false;
			message = "Mật khẩu quá ngắn, cần lớn hơn 5 ký tự";
			document.getElementById('pass2').disabled = false;
		}else{
			let name = document.getElementById('name').value;
			
			if((name!="")&&(pass1.indexOf(name)!= -1)){
				validUserPass = false;
				message = "Mật khẩu không nên chứa tên đăg nhập";
				document.getElementById('pass2').disabled = true;
			}else{
				document.getElementById('pass2').disabled = false;
				if(pass2!=""){
					if(pass1!=pass2){
						validUserPass = false;
						message = "Mật khẩu xác nhận lại chưa đúng";
					}
				}else{
					validUserPass = false;
					message = "Hãy nhập lại mật khẩu";
				}
			}
		}
	}
	
	//xuất tb
	if(!validUserPass){
		errPass.innerHTML = message;
		errPass.style.color = "red";
	}else{
		errPass.innerHTML ='<i class="fa-solid fa-check"></i>';
		errPass.style.color = "blue";
	}
	
	return validUserPass;
}

function checkUseremail(){
	let email = document.getElementById('email').value;
	
	var validUserEmail = true;
	
	var message = "";
	
	//tham chieeus doi tuong bao loi
	let errEmail = document.getElementById('errEmail');
	
	if(email.trim()== ""){
		validUserEmail = false;
		message = "Thiếu hộp thư đăng nhập vào hệ thống";
	}else if(email.indexOf("@")!= -1){
		var parttern = /\w+@\w+[.]\w/;
		if(!email.match(parttern)){
		validUserEmail = false;
		message = "Không đúng cấu trúc hộp thư";

		}
	}else{
		validUserEmail = false;
		message = "Hãy nhập cấu trúc hộp thư chuẩn";
	}
	
	if(!validUserEmail){
		errEmail.innerHTML = message;
		errEmail.style.color = "red";
	}else{
		errEmail.innerHTML ='<i class="fa-solid fa-check"></i>';
		errEmail.style.color = "blue";
	}
	
	return validUserEmail;
}


function checkPermis(){
	let permis = parseInt(document.getElementById('permis').value);
	
	var validPermis = true;
	var errRoles_hidden = false;
	var message = "Cần có ít nhất một vai trò cho quyền này";
	
	let errRoles = document.getElementById('errRoles');
	
	//tham chiếu form
	if(permis == 2 || permis == 3){
		let fn = document.getElementById('frmUser');
	
		for(var i=0; i<fn.elements.length; i++){
			if((fn.elements[i].type == "checkbox") && (fn.elements[i].name == "chks")){
				if(fn.elements[i].checked){
					validPermis = true;
					break;
				}else{
					validPermis = false;
				}
			}
		}
	}else{
		var errRoles_hidden = true;
	}
	
	//xuất thông báo
	if(!validPermis){
		errRoles.style.display = "block";

		errRoles.innerHTML = message;
		errRoles.style.color = "red";
	}else{
		if(errRoles_hidden){
			errRoles.style.display = "none";
		}else{
			errRoles.style.display = "block";
			errRoles.innerHTML ='<i class="fa-solid fa-check"></i>';
			errRoles.style.color = "blue";
		}
	}
	
	return validPermis;
}

function checkValidUser(){
	//ktra tên
	let checkName = this.checkUsername();
	
	//ktra pass
	
	let checkPass = this.checkUserpass();
	
	//ktra email
	
	let checkEmail = this.checkUseremail();
	let email = document.getElementById("email");
	if(email.disabled){
		checkEmail = true;
	}
	 
	 if(!checkName){
		 document.getElementById('name').focus();
		 document.getElementById('name').select();
	 }else if(!checkPass){
		 document.getElementById('pass').focus();
		 document.getElementById('pass').select();
	 }else if(!checkEmail){
		 document.getElementById('email').focus();
		 document.getElementById('email').select();
	 }
	 
	 return checkName && checkPass && checkEmail;
}

function createAccount(fn){
	if(this.checkValidUser()){
		fn.method = "Post";
		fn.action = "/adv/user/add";
		fn.submit();
	}
}


function editProfile(fn){
	fn.method = "Post";
	fn.action = "/adv/user/profiles/edit";
	fn.submit();
}

function checkValidChangePass(fn){
	//lấy giá trị newpass và renewpass
	let newpass = document.getElementById("newPassword").value;
	let renewpass = document.getElementById("renewPassword").value;
	
	var validChangePass = true;
	
	//Đường dẫn thông báo lỗi
	let err = document.getElementById("iconNewPass");
	let err1 = document.getElementById("iconReNewPass");
	newpass = newpass.trim();
	renewpass = renewpass.trim();
	
	if((newpass != "") && (renewpass != "")){
		if(newpass.length < 6){
			validChangePass = false;
		}else{
			if(newpass == renewpass){
				validChangePass = true;
			}else{
				validChangePass = false;
			}
		}
	}else{
		validChangePass = false;
	}
	
	if(!validChangePass){
		err.innerHTML = "<i class=\"fa-solid fa-xmark\"></i>";
		err.style.color = "#dc3545";
		err.style.fontSize = "16px";
		err1.innerHTML = "<i class=\"fa-solid fa-xmark\"></i>";
		err1.style.color = "#dc3545";
		err1.style.fontSize = "16px";
	}else{
		err.innerHTML = "<i class=\"fa-solid fa-check\"></i>";
		err.style.color = "#dc3545";
		err.style.fontSize = "16px";
		err1.innerHTML = "<i class=\"fa-solid fa-check\"></i>";
		err1.style.color = "#dc3545";
		err1.style.fontSize = "16px";
	}
	return validChangePass;
}

function changePass(fn){
	if(this.checkValidChangePass()){
		fn.method = "POST";
		fn.action = "/adv/user/profiles/changepass";
		fn.submit();
	}
}