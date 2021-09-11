let valid;

function form_control(){
	let elem = document.getElementsByName('fio')[0];
	let error = document.getElementById('error');
	let fio = elem.value;
	fio = fio.trim();
	
	if (fio == "") {
		elem.style.backgroundColor = "red";
		error.innerHTML = "Введите ФИО";
		return valid = false;
	}
	
	let fio_spl = fio.split(" ");
	if (fio_spl.length < 2){
		elem.style.backgroundColor = "red";
		error.innerHTML = "Введите ФИО";
		return valid = false;
	}
	
	elem.style.backgroundColor = "";
	error.innerHTML = "";
	return valid = true;
};

function valid_data(){
	return form_control();
};
