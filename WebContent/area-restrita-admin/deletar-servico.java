<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>

  <div class="container">
    <div class="section-header">
      <h2>Deletar Serviços</h2>
    </div>
  
    <div class="form">
      <form action="servico" method="post">
          <div class="form-group col-md-6">
            <label>Id</label>
              <input type="number" name="id" class="form-control" value=""
              placeholder="Digite o id do serviço" required="required">
          </div>
        <div class="form-group float-right">
          <button type="submit" class="btn btn-default" onclick="removerServico()">Excluir</button>
        </div>
      </form>
    </div>

  </div>
</section>

<%@ include file="../includes/footer.jsp"%>