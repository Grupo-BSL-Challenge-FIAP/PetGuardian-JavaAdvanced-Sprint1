# 🐾 Vitalia API

O Vitalia é uma plataforma de monitoramento inteligente para animais de estimação, integrando tutores, veterinários e dispositivos IoT para garantir a saúde e o bem-estar animal.

---

# 🚀 Swagger UI & Consoles

Acesse a documentação interativa e realize testes diretamente pelo navegador:

```bash
https://petguardian-api-p2jq.onrender.com/swagger-ui/index.html#/
```
```bash
http://localhost:8080/swagger-ui/index.html
```
```bash
http://localhost:8080/api-docs
```
```bash
http://localhost:8080/h2-console
```
```bash
http://localhost:8080
```
---

# Cronograma 

| Atividade | Responsável | Data | Status |
|--------|----------|------------|------------|
| Implementação da entidade Account | Manuelalacerda | 13/05 |Concluído |
| Configuração inicial do Swagger/OpenAPI | Manuelalacerda | 13/05 |Concluído |
| Criação do README e guia de testes | Manuelalacerda | 13/05 |Concluído |
| Implementação da entidade Responsible | Manuelalacerda | 13/05 |Concluído |
| Ajustes de validações do AccountRequest | Manuelalacerda | 15/05 |Concluído |
| Ajustes de validações do ResponsibleRequest | Manuelalacerda | 15/05 |Concluído |
| Implementação da entidade Veterinarian | Manuelalacerda | 15/05 |Concluído |
| Implementação da entidade Pet | Manuelalacerda | 15/05 |Concluído |
| Ajustes e correções dos endpoints | Manuelalacerda | 15/05 |Concluído |
| Implementação da entidade ClinicalHistory | Manuelalacerda | 15/05 |Concluído |
| Documentação do módulo ClinicalHistory | Manuelalacerda | 15/05 |Concluído |
| Implementação da entidade Alert | Manuelalacerda | 15/05 |Concluído |
| Implementação da entidade Appointment | Manuelalacerda | 15/05 |Concluído |
| Correção de paginação e cache do módulo Pet | Manuelalacerda | 18/05 |Concluído |
| Correção de paginação e cache do módulo Responsible | Manuelalacerda | 18/05 |Concluído |
| Configuração de cache na aplicação | Manuelalacerda | 18/05 |Concluído |
| Ajustes do SwaggerConfig | Manuelalacerda | 18/05 |Concluído |
| Correção dos HTTP Responses | Manuelalacerda | 18/05 |Concluído |
| Organização de imagens e estrutura do projeto | Manuelalacerda | 21/05 |Concluído |
| Atualizações da documentação README | Manuelalacerda | 21/05 |Concluído |
| Adição da pasta de testes e exportação Postman | Manuelalacerda | 21/05 |Concluído |

---

## 👥 Integrantes do Grupo

<table>
  <tr>
    <td width="130">
      <img src="https://github.com/moisesBarsoti.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Moisés Barsoti Andrade de Oliveira</b><br/>
      <b>RM:</b> 565049 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>

  <tr>
    <td width="130">
      <img src="https://github.com/sSofia-s.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Sofia Siqueira Fontes</b><br/>
      <b>RM:</b> 563829 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>

  <tr>
    <td width="130">
      <img src="https://github.com/manuelalacerda.png" width="120" style="border-radius: 50%;"/>
    </td>
    <td>
      <b>Manuela de Lacerda Soares</b><br/>
      <b>RM:</b> 564887 &nbsp;&nbsp;|&nbsp;&nbsp;<b>Turma:</b> 2TDSPG - FIAP <br/>
    </td>
  </tr>
</table>

---


# 🔑 1. Accounts 

Módulo base para autenticação e controle de perfis (`TUTOR`, `VETERINARIAN`, `ADMIN`).

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/accounts` | Cria uma nova conta de acesso |
| GET | `/accounts` | Lista todas as contas (Paginado) |
| GET | `/accounts/{id}` | Busca detalhes de uma conta específica |
| PUT | `/accounts/{id}` | Atualiza e-mail, senha ou status |
| DELETE | `/accounts/{id}` | Remove uma conta do sistema |

## Exemplo de Cadastro (POST)

### JSON

```json
{
  "email": "contato@petguardian.com",
  "password": "SenhaSegura123",
  "role": "VETERINARIAN",
  "active": true
}
```

---

# 👨‍⚕️ 2. Veterinarians

Gerenciamento de profissionais e registros CRMV.

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/veterinarians` | Cadastra um veterinário vinculado a uma Account |
| GET | `/veterinarians` | Lista profissionais e especialidades |
| GET | `/veterinarians/{id}` | Busca detalhes de um veterinário específico |
| PUT | `/veterinarians/{id}` | Atualiza dados profissionais ou especialidade |
| DELETE | `/veterinarians/{id}` | Remove o registro do veterinário |

## Exemplo de Cadastro (POST)

### JSON

```json
{
  "fullName": "Dr. Ricardo Santos",
  "cpf": "123.456.789-00",
  "crmv": "CRMV-SP 12345",
  "speciality": "Cardiologia",
  "accountId": 1
}
```

---

# 🏠 3. Responsibles 

Informações de contato e localização dos donos dos pets.

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/responsibles` | Cadastra um novo tutor responsável |
| GET | `/responsibles` | Lista todos os responsáveis cadastrados |
| GET | `/responsibles/{id}` | Busca um responsável pelo ID |
| GET | `/responsibles/search` | Busca responsáveis por nome |
| PUT | `/responsibles/{id}` | Atualiza endereço ou telefone de contato |
| DELETE | `/responsibles/{id}` | Remove o registro do responsável |

## Exemplo de Cadastro (POST)

### JSON

```json
{
  "fullName": "Manuela de Lacerda Soares",
  "cpf": "987.654.321-11",
  "dateOfBirth": "1995-05-15",
  "phoneNumber": "(11) 98765-4321",
  "address": "Rua das Flores, 123 - São Paulo",
  "accountId": 2
}
```

---

# 🐶 4. Pets

O coração do sistema, vinculando o animal ao seu tutor e médico responsável.

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/pets` | Cadastra um pet e define seu status inicial |
| GET | `/pets` | Lista todos os pets e seus estados de saúde |
| GET | `/pets/{id}` | Detalhes biométricos de um pet específico |
| GET | `/pets/search/name` | Busca pets por nome |
| GET | `/pets/search/species` | Busca pets por espécie |
| GET | `/pets/search/status` | Busca pets por status |
| PUT | `/pets/{id}` | Atualiza peso, status ou dados do anim
| DELETE | `/pets/{id}` | Remove um pet do sistema |

## Exemplo de Cadastro (POST)

### JSON

```json
{
  "name": "Thor",
  "species": "Cão",
  "breed": "Golden Retriever",
  "gender": "Macho",
  "birthDate": "2021-03-10",
  "weight": 32.5,
  "currentStatus": "NORMAL",
  "responsibleId": 1,
  "veterinarianId": 1
}
```

# 📊 5. Clinical History 
Este módulo é o núcleo de monitoramento do ecossistema, onde são registrados os dados biométricos (temperatura, batimentos, nível de atividade)

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/clinical-histories` | Registra novas métricas de saúde para um pet |
| GET | `/clinical-histories` | Lista todos os registros clínicos (Paginado) |
| GET | `/clinical-histories/{id}` | Busca um registro específico por ID |
| GET | `/clinical-histories/pet/{petId}` | Lista o histórico completo de um animal específico |
| PUT | `/clinical-histories/{id}` | Atualiza ou corrige dados de um registro existente |
| DELETE | `/clinical-histories/{id}` | Remove um registro do histórico clínico |

```json
{
  "temperature": 38.5,
  "heartRate": 85,
  "activityLevel": "HIGH",
  "healthScore": 95.0,
  "description": "Monitoramento via ESP32",
  "observations": "Dados coletados após exercício matinal",
  "petId": 1
}
```

# 🚨 6. Alerts (Alertas Preventivos)
Este módulo gerencia as notificações geradas pelo sistema com base no monitoramento dos pets.

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/alerts` | Registra um novo alerta preventivo  |
| GET | `/alerts` | Lista todos os alertas registrados  |
| GET | `/alerts/{id}` | Busca os detalhes de um alerta específico |
| GET | `/alerts/pet/{petId}` | Lista o histórico de alertas de um animal específico |
| PUT | `/alerts/{id}` | Atualiza o alerta e o status |
| DELETE | `/alerts/{id}` | Remove um alerta do sistema |

```json
{
  "type": "TEMPERATURE",
  "message": "Febre detectada: 40.2°C. O animal já foi medicado.",
  "riskLevel": "HIGH",
  "status": "RESOLVED",
  "petId": 1
}
```

# 📅 7. Appointments 
Este módulo gerencia o agendamento, diagnóstico e acompanhamento clínico realizado pelos veterinários.

| Método | Endpoint | Descrição |
|--------|----------|------------|
| POST | `/appointments` | Agenda uma nova consulta veterinária|
| GET | `/appointments` | Lista todas as consultas agendadas |
| GET | `/appointments/{id}` | Busca detalhes de uma consulta específica |
| PUT | `/appointments/{id}` | Atualiza dados ou status  |
| DELETE | `/appointments/{id}` | Cancela ou remove uma consulta do cronograma |

```json
{
  "appointmentDate": "2026-06-20T14:30:00",
  "reason": "Consulta de rotina e check-up semestral",
  "petId": 1,
  "veterinarianId": 1
}
```
### 🗄 Modelo 
<div align="center">
  <img src="doc/Pet%20Care%20Management%20Model-2026-05-18-150201.png" alt="Modelo de Gestão de Cuidados de Pets" width="200" />
  <img src="doc/Pet%20Care%20Management%20Model-2026-05-18-150244.png" alt="Modelo de Gestão de Cuidados de Pets" width="305" />
  <img src="doc/Pet%20Care%20Management%20Model-2026-05-18-150318.png" alt="Modelo de Gestão de Cuidados de Pets" width="215" />
</div>


