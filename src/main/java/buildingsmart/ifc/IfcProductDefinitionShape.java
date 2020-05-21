package buildingsmart.ifc;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * The <i>IfcProductDefinitionShape</i> defines all shape relevant information
 * about an <i>IfcProduct</i>. It allows for multiple geometric shape
 * representations of the same product.
 */
public class IfcProductDefinitionShape extends IfcProductRepresentation {
    //private IfcProduct[] ShapeOfProduct;
    //private IfcShapeAspect[] HasShapeAspects;

    /**
     * @param name            The word or group of words by which the product
     *                        representation is known.
     * @param description     The word or group of words that characterize the
     *                        product representation. It can be used to add
     *                        additional meaning to the name of the product
     *                        representation.
     * @param representations Contained list of representations (including shape
     *                        representations). Each member defines a valid
     *                        representation of a particular type within a
     *                        particular representation context.
     * @throws IllegalArgumentException If representations is null, or if its
     *                                  size is lower than 1, or if it contains
     *                                  objects that are not of type
     *                                  IfcShapeModel.
     */
    public IfcProductDefinitionShape(IfcLabel name, IfcText description,
                                     @NotNull
                                             List<IfcRepresentation> representations) {
        super(name, description, representations);
        for (IfcRepresentation repr : representations) {
            if (!(repr instanceof IfcShapeModel)) {
                throw new IllegalArgumentException(
                        "representations must contain only objects of type " +
                                "IfcShapeModel");
            }
        }
    }

    /**
     * @param name            The word or group of words by which the product
     *                        representation is known.
     * @param description     TheThe <i>IfcProductDefinitionShape</i>
     *                        <p>
     *                        defines all shape relevant information about an
     *                        <i>IfcProduct</i>.
     *                        <p>
     *                        It allows for multiple geometric shape
     *                        representations of the same
     *                        <p>
     *                        product. word or group of words that characterize
     *                        the product representation. It can be used to add
     *                        additional meaning to the name of the product
     *                        representation.
     * @param representations Contained list of representations (including shape
     *                        representations). Each member defines a valid
     *                        representation of a particular type within a
     *                        particular representation context.
     * @throws IllegalArgumentException If representations is null, or if its
     *                                  size is lower than 1, or if it contains
     *                                  objects that are not of type
     *                                  IfcShapeModel.
     */
    public IfcProductDefinitionShape(IfcLabel name, IfcText description,
                                     @NotNull
                                             IfcRepresentation... representations) {
        this(name, description, Arrays.asList(representations));
    }
}
