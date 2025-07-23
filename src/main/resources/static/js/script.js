function loadDataEdit(element) {

	const employeeId = element.dataset.id;

	const trEmployee = element.closest('tr');

	const employeeUsername = trEmployee.children[0].textContent;
	const employeeName = trEmployee.children[1].textContent;
	const employeeSurname = trEmployee.children[2].textContent;

	document.getElementById("employeeUsernameModal").value = employeeUsername;
	document.getElementById("employeeNameModal").value = employeeName;
	document.getElementById("employeeSurnameModal").value = employeeSurname;

	document.getElementById("employeeIdModal").value = employeeId;



	const form = document.getElementById('editEmployeeModal');
	form.action = "/timeTracking/employees/" + employeeId + "/edit";

	showErrorsModal();
}

function showErrorsModal(errors = {}) {

	document.querySelectorAll(".alert-danger").forEach(alert => {
		alert.innerHTML = "";
		alert.style.display = "none";
		alert.classList.add("d-none");
	});

	for (const field in errors) {
		const alertDiv = document.getElementById(`error-${field}`);
		if (alertDiv) {
			alertDiv.innerHTML = errors[field].join("<br>");
			alertDiv.style.display = "block";
			alertDiv.classList.remove("d-none");
		}
	}

}

function reloadListEmployees(e) {

	e.preventDefault();

	const form = document.getElementById("editEmployeeModal");
	const url = form.action;
	const formData = new FormData(form);
	const id = document.getElementById("employeeIdModal").value;

	fetch(url, {
		method: "POST",
		body: formData,
		headers: {
			"X-Requested-With": "XMLHttpRequest"
		}
	})
		.then(response => {
			if (!response.ok) {
				return response.json().then(errors => {
					showErrorsModal(errors);
				});
			} else {
				return response.json().then(updatedEmployee => {

					const modalElement = document.getElementById("modalEdit");
					const modalInstance = bootstrap.Modal.getInstance(modalElement);
					modalInstance.hide();

					const row = document.querySelector(`a[data-id="${id}"]`).closest("tr");
					row.children[0].textContent = updatedEmployee.username;
					row.children[1].textContent = updatedEmployee.name;
					row.children[2].textContent = updatedEmployee.surname;

					showErrorsModal();

				});

			}

		})
		.catch(error => {
			console.error("No se ha podido enviar el formulario", error);
		});
}




function isEmpty(field) {

	return document.getElementById(field).value.trim() === "";

}

function errorsFormModal(e) {

	if (isEmpty("employeeUsernameModal") || isEmpty("employeeNameModal") || isEmpty("employeeSurnameModal")) {
		e.preventDefault();
	}

}

function resetModal() {

	const formModal = document.getElementById("modalEdit");
	formModal.classList.remove("show", "d-block");
	formModal.style.display = "none";
	formModal.removeAttribute("aria-modal");
	formModal.removeAttribute("role");

	document.querySelectorAll(".alert-danger").forEach(alert => {
		alert.innerHTML = "";
		alert.style.display = "none";
	});

}

//document.getElementById("editEmployeeModal").addEventListener('submit', errorsFormModal)
document.querySelectorAll(".linkEditModal").forEach(link => {
	link.addEventListener("click", function() {
		loadDataEdit(this);
	});
});

document.getElementById("btnSaveEmp").addEventListener("click", function(e) {
	reloadListEmployees(e);
});

document.getElementById("btnCloseEmp").addEventListener("click", resetModal);
document.getElementById("modalEdit").addEventListener("hidden.bs.modal", resetModal);
