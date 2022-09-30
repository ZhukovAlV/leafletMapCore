package tetramap.layer.groups;

import tetramap.layer.Layer;

public interface LayerGroupFunctions {

    /**
     * Adds the given layer to the group.
     *
     * @param layer the layer to be added to this layer group
     */
    void addLayer(Layer layer);

    /**
     * Removes the given layer from the group.
     *
     * @param layer the layer to be removed from this layer group
     */
    void removeLayer(Layer layer);

    /**
     * Removes the layer with the given internal ID from the group.
     *
     * @param layerId the internal ID of the layer should remove
     */
    void removeLayer(String layerId);

    /**
     * Returns true if the given layer is currently added to the group.
     *
     * @param layer layer to be check
     * @return true if the given layer is currently added to the group.
     */
    boolean hasLayer(Layer layer);

    /**
     * Returns true if the given internal ID is currently added to the group.
     *
     * @param layerId layer to be check
     * @return true if the given internal ID is currently added to the group.
     */
    boolean hasLayer(String layerId);

    /**
     * Removes all the layers from the group.
     */
    void clearLayers();
}
