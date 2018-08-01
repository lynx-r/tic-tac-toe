package tic_tac_toe.spec.common;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

public abstract class Spec {

    public static <Entity> Specification<Entity> fetchLeft(PluralAttribute<Entity, ?, ?> pluralAttribute) {
        return (root, query, cb) -> {
            if (!isCountQuery(query)) {
                root.fetch(pluralAttribute, JoinType.LEFT);
                query.distinct(true);
            }
            return null;
        };
    }

    public static <Entity> Specification<Entity> fetchLeft(SingularAttribute<Entity, ?> singularAttribute) {
        return (root, query, cb) -> {
            if (!isCountQuery(query)) {
                root.fetch(singularAttribute, JoinType.LEFT);
                query.distinct(true);
            }
            return null;
        };
    }

    private static boolean isCountQuery(CriteriaQuery<?> query) {
        return query.getResultType() == Long.class || query.getResultType() == long.class;
    }

    public static <Entity, FieldType> Specification<Entity> fieldIs(
            SingularAttribute<Entity, FieldType> singularAttribute, FieldType fieldValue)
    {
        return (root, query, cb) -> cb.equal(root.get(singularAttribute), fieldValue);
    }
}
