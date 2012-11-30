package entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2012-11-27T23:34:43")
@StaticMetamodel(ExperienciaAnterior.class)
public class ExperienciaAnterior_ { 

    public static volatile SingularAttribute<ExperienciaAnterior, Long> id;
    public static volatile SingularAttribute<ExperienciaAnterior, String> dataFim;
    public static volatile SingularAttribute<ExperienciaAnterior, Long> idEmpresa;
    public static volatile SingularAttribute<ExperienciaAnterior, Double> salario;
    public static volatile SingularAttribute<ExperienciaAnterior, String> descricaoAtv;
    public static volatile SingularAttribute<ExperienciaAnterior, String> dataInicio;
    public static volatile SingularAttribute<ExperienciaAnterior, String> flagAprovado;
    public static volatile SingularAttribute<ExperienciaAnterior, Long> idCandidato;

}