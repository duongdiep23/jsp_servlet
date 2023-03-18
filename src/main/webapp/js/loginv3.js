//cac xu ly kich ban cho loginv3.html

function checkValidUser(fn){
	//lay gia tri thong tin dang nhap tren giao dien
	let username = document.getElementById("user").value;
	let userpass = document.getElementById("pass").value;
	//bien ghi nhan tb
	var message = "";
	//bien ktra tung thanh phan thong tin
	var validUsername = true;
	var validUserpass = true;
	
	//bien hien thi tb loi
	let viewErrName = document.getElementById("errUser");
	let viewErrPass = document.getElementById("errPass");
	
	//ktra username
	username = username.trim();
	if(username == ""){
		validUsername = false;
		message = "Thiếu tên đăng nhập vào hệ thống";
	}else{
		if((username.length < 5) || (username.length > 50)){
			validUsername = false;
			message = "Tên đăng nhập vào hệ thống quá ngắn hoặc quá dài";
		}else{
			if(username.indexOf(" ")!= -1){
				validUsername = false;
				message = "Tên đăng nhập chứa dấu cách bên trong";
			}else{
				if(username.indexOf("@")!= -1){
					let parttern = /\w+@\w+[.]\w/;//cau truc hop thu
					if(!username.match(parttern)){
						validUsername = false;
						message = "Không đúng cấu trúc hộp thư";
					}
				}
			}
		}
	}
	
	//xuat thong bao cua username
	if(!validUsername){
		viewErrName.innerHTML = message;
		viewErrName.style.color = "red";
		viewErrName.style.fontWeight = "bold";
	}else{
		viewErrName.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrName.style.color = "red";
		viewErrName.style.fontWeight = "bold";
	}
	
	//Kiem tra Userpass
	userpass = userpass.trim();
	if(userpass == ""){
		validUserpass = false;
		message = "Thiếu mật khẩu vào hệ thống";
	}else{
		if(userpass.length<6){
			validUserpass = false;
			message = "Mật khẩu quá ngắn, cần lớn hơn 5 ký tự";
		}
	}
	
	//xuat tb userpass
	if(!validUserpass){
		viewErrPass.innerHTML = message;
		viewErrPass.style.color = "red";
		viewErrPass.style.fontWeight = "bold";
	}else{
		viewErrPass.innerHTML = '<i class="fa-solid fa-check"></i>';
		viewErrPass.style.color = "red";
		viewErrPass.style.fontWeight = "bold";
	}
	
	//tra ve trang thai ktra tong the
	return validUsername && validUserpass;
}

function login(fn){
	if(this.checkValidUser(fn)){
		fn.method = "post";//gọi vào phương thức doPost
		fn.action = "/adv/user/login";//đường dẫn thực thi của Servlet
		fn.submit();
	}
}
