function carregarNav() {
    fetch('/src/nav.html')
        .then(response => response.text())
        .then(data => {
            document.querySelector('#nav').innerHTML = data;
        })
        .catch(error => console.error('Erro ao carregar o arquivo HTML:', error));
}

function redirecionaHome() {
    window.location.href = '/src/index.html';
}

carregarNav();

/* CODIGOS COMUM */

async function listarAlunos() {
    try {
        const response = await fetch("http://localhost:8080/api/alunos");

        if (!response.ok) {
            throw new Error(`Erro de Rede: ${response.statusText}`);
        }

        const data = await response.json();

        var tabela = document.getElementById('table');
        var tbody = tabela.getElementsByTagName('tbody')[0];

        data.forEach(function (elemento) {
            var linha = tbody.insertRow();
            var celulaId = linha.insertCell(0);
            var celulaNome = linha.insertCell(1);
            var celulaEmail = linha.insertCell(2);

            celulaId.textContent = elemento.id;
            celulaNome.textContent = elemento.nome;
            celulaEmail.textContent = elemento.email;

        });

    } catch (error) {
        alert('Erro ao listar alunos: ' + error.message);
        throw error;
    }
}
listarAlunos();