<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ include file="../includes/header.jsp"%>

<section>

  <div class="container">
    <div class="section-header">
      <h2>Alterar Informações</h2>
    </div>
  
    <div class="form">
      <form action="servico" method="post">

        <div class="form-row">
          <input type="number" name="id" value="" style="display: none;">
          
          <div class="form-group col-md-6">
            <label>Descrição</label>
            <input type="text" name="descricao" class="form-control" value=""
              placeholder="Digite a descrição" required="required">
          </div>
          
          <div class="form-group col-md-6">
            <label>Caracteristica</label>
            <input type="text" class="form-control" name="características" value=""
               placeholder="Digite as características" required="required">
          </div>
        </div>
        
        <div class="form-row">
          <div class="form-group col-md-4">
            <label>Valores</label>
            <input type="number" class="form-control" name="valorpagina" value=""
            placeholder="Digite o valor por página" maxlength="14">

            <input type="number" class="form-control" name="valorlauda" value=""
            placeholder="Digite o valor por lauda" maxlength="14">
            
            <input type="number" class="form-control" name="valorpalavra" value=""
            placeholder="Digite o valor por character" maxlength="14">
          </div>
        
        <div class="form-group float-right">
          <button type="submit" class="btn btn-default">Salvar</button>
        </div>
      </form>
    </div>

  </div>
</section>

<%@ include file="../includes/footer.jsp"%>