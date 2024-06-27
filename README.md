# Projeto Final de Orientação a Objetos 2 - UTFPR

## Informações Gerais

O projeto final da disciplina de Orientação a Objetos 2 consiste na implementação de um software de agenda e calendário, similar ao Google Calendar, porém com foco em acesso local e suporte para múltiplos usuários. O sistema é desenvolvido em Java utilizando a arquitetura em camadas, garantindo separação clara entre a interface gráfica, a lógica de negócio e a persistência de dados.

## Funcionalidades

### Controle de Acesso
- Sistema de login com autenticação para múltiplos usuários.
- Cadastro de novos usuários com nome de usuário único e senha.

### Gestão de Usuário
- Edição de informações pessoais (nome completo, data de nascimento, gênero, e-mail, nome de usuário e senha).
- Exclusão de conta.

### Gestão de Agendas
- Criação, visualização, edição e exclusão de agendas.
- Cada agenda possui um nome e descrição.

### Gestão de Compromissos
- Criação, visualização, edição e exclusão de compromissos.
- Compromissos possuem título, descrição, data e hora de início e término, local, agenda, pessoas convidadas e data e hora de notificação.

### Convites
- Convite de usuários para compromissos.
- Aceitação ou recusa de convites.
- Compromissos aceitos são adicionados à agenda do usuário convidado.

### Interface do Usuário
- Interface gráfica intuitiva e amigável (implementada com JFrame).

## Pré-requisitos

1. **Instalar o NetBeans IDE**: 
   - Baixe e instale o NetBeans IDE a partir do site oficial: [NetBeans Downloads](https://netbeans.apache.org/download/index.html).
   - Certifique-se de que o JDK está corretamente configurado no NetBeans.

2. **Instalar driver JDBC**:
   - Baixe e instale o connetor PostgreSQL a partir do site oficial: [PostgresJDBC]([https://netbeans.apache.org/download/index.html](https://jdbc.postgresql.org/download/)).
  
3. **Importar o Projeto no NetBeans**:
   - Abra o NetBeans IDE.
   - Selecione `File` > `Open Project`.
   - Navegue até o diretório onde o projeto foi descompactado e selecione a pasta do projeto.

4. **Configurar Bibliotecas Extras**:
   - Se o projeto utilizar bibliotecas adicionais, certifique-se de que a pasta `libs` está incluída no classpath do projeto. Para isso, clique com o botão direito do mouse no projeto no NetBeans, selecione `Properties`, vá até `Libraries` e adicione a pasta `libs`.

5. **Executar o Projeto**:
   - Clique com o botão direito do mouse no projeto e selecione `Run`.

## Equipe de Desenvolvimento
- **César Arandas Silva** - [@CzR21](https://github.com/CzR21)
- **Rebeca Torrecilhas** - [@rtorrecilhas](https://github.com/rtorrecilhas)
