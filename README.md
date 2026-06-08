# HP API Android

Aplicativo Android desenvolvido como trabalho acadêmico para consumo da [HP-API](https://hp-api.onrender.com/), demonstrando o uso de corrotinas e web services em Kotlin.

> 20% da nota final · Grupos de 3 a 4 integrantes · API 29 Android

---

## Funcionalidades

- **Listar personagem por ID** — exibe nome, espécie, casa e foto
- **Listar professor da escola** — busca por nome, exibe nome, nomes alternativos, espécie e casa
- **Listar estudantes de uma casa** — seleção via Radio Buttons, listagem em RecyclerView com foto, nome e casa
- **Ver feitiços** — lista completa com tela de detalhes (nome e descrição)
- **Sair** — encerra o aplicativo

---

## Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Kotlin |
| Min SDK | API 29 (Android 10) |
| HTTP | Retrofit 2 |
| Assincronismo | Coroutines + ViewModelScope |
| Imagens | Coil |
| UI binding | ViewBinding |
| Arquitetura | MVVM (simples) |

---

## Estrutura do Projeto

```
app/src/main/java/com/seu/pacote/
├── data/
│   ├── model/          # Character, Spell
│   └── api/            # RetrofitClient, HpApiService
├── ui/
│   ├── main/           # MainActivity
│   ├── character/      # CharacterActivity
│   ├── staff/          # StaffActivity
│   ├── students/       # StudentsActivity, StudentAdapter
│   └── spells/         # SpellsActivity, SpellDetailActivity, SpellAdapter
└── utils/              # Extensions, helpers
```

---

## Como rodar

1. Clone o repositório
   ```bash
   git clone https://github.com/seu-usuario/hp-api-android.git
   ```
2. Abra no Android Studio (Electric Eel ou superior)
3. Aguarde o Gradle sincronizar
4. Rode em um emulador ou dispositivo com API 29+

> Nenhuma chave de API é necessária — a HP-API é pública.

---

## Endpoints utilizados

| Funcionalidade | Endpoint |
|---|---|
| Personagem por ID | `GET /api/character/{id}` |
| Professores | `GET /api/characters/staff` |
| Estudantes por casa | `GET /api/characters/house/{house}` |
| Feitiços | `GET /api/spells` |

---

## Entrega

Repositório enviado via tarefa no Microsoft Teams conforme instruções do professor.
