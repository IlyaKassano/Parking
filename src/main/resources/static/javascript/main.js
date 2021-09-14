let valid;

function clientFormControl(){
	let elem = document.getElementsByName('fio')[0];
	let error = document.getElementById('err');
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
	return valid;
};

function parkingFormControl(){
	let elem1 = document.getElementsByName('dateParking')[0];
	let elem2 = document.getElementsByName('dateDepart')[0];
	let error = document.getElementById('error');
	let dateParking = elem1.value;
	let dateDepart = elem2.value;

	if((dateDepart < dateParking) && dateDepart != null && dateDepart != "") {
		elem2.style.backgroundColor = "red";
		error.innerHTML = "Дата убытия не может быть больше даты парковки!";
		return valid = false;
	}

	elem2.style.backgroundColor = "";
	error.innerText.innerHTML = "";
	return valid = true;
}
