package sistema_inventario.service;
import java.util.List;
import org.springframework.stereotype.Service;
import sistema_inventario.model.Producto;
import sistema_inventario.repository.ProductoRepository;
import java.util.Optional;
import sistema_inventario.dto.ProductoRequestDTO;






@Service 

public class ProductoService { 
    private final ProductoRepository repository;
public ProductoService(ProductoRepository repository) {
this.repository = repository; 
    
    } 
    public List<Producto> listarProductos() {
    return repository.findAll();
}
        public Optional<Producto> buscarPorId(Long id) {
    return repository.findById(id); } 

    public void eliminarProducto(Long id) { 
    repository.deleteById(id); } 

    public Producto actualizarProducto(Long id, ProductoRequestDTO dto) { 
        return repository.findById(id) .map(producto -> {
            producto.setCodigo(dto.getCodigo());
            producto.setNombre(dto.getNombre());
            producto.setCategoria(dto.getCategoria());
            producto.setPrecio(dto.getPrecio());
            producto.setCantidad(dto.getCantidad());
            return repository.save(producto);
        }).orElse(null);
    }
    public Producto registrarProducto(ProductoRequestDTO dto) {
    if (dto.getPrecio() == null || dto.getPrecio() <= 0) {
        throw new IllegalArgumentException("El precio debe ser mayor a 0");
    }

    Producto producto = new Producto();
    producto.setCodigo(dto.getCodigo());
    producto.setNombre(dto.getNombre());
    producto.setCategoria(dto.getCategoria());
    producto.setPrecio(dto.getPrecio());
    producto.setCantidad(dto.getCantidad());

    return repository.save(producto);
}
}
