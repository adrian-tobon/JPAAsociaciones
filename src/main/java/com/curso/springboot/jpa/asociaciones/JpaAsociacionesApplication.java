package com.curso.springboot.jpa.asociaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.springboot.jpa.asociaciones.entities.Address;
import com.curso.springboot.jpa.asociaciones.entities.Client;
import com.curso.springboot.jpa.asociaciones.entities.ClientDetail;
import com.curso.springboot.jpa.asociaciones.entities.Course;
import com.curso.springboot.jpa.asociaciones.entities.Invoice;
import com.curso.springboot.jpa.asociaciones.entities.Student;
import com.curso.springboot.jpa.asociaciones.repositories.AddressRepository;
import com.curso.springboot.jpa.asociaciones.repositories.ClientDetailsRepository;
import com.curso.springboot.jpa.asociaciones.repositories.ClientRepository;
import com.curso.springboot.jpa.asociaciones.repositories.CourseRepository;
import com.curso.springboot.jpa.asociaciones.repositories.InvoiceRepository;
import com.curso.springboot.jpa.asociaciones.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class JpaAsociacionesApplication implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ClientDetailsRepository clientDetailRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaAsociacionesApplication.class, args);
	}

	@SuppressWarnings("null")
	@Override
	public void run(String... args) throws Exception {
		// manyToOne();
		// findClientById();		
		// createInvoice();
		// createClientWithAddress();
		// deleteClientWithAddress();
		// findClientByIdWithAddresses();
		// addAddresesExitingClient();
		// deleteAddress();
		// deleteInvoice();
		// createClientDetail();
		
		// addCourseToStudent();
		 deleteStudent();
		// deleteCourseFromStudent();
		// deleteCourse();
		 
		
	}
	
	@Transactional
	public void deleteCourse() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacio curso---------------");
		System.out.println("Ingrese el id del curso:");
		Long id  = scanner.nextLong();
		
		Optional<Course> optCourse = courseRepository.findById(id);
		
		optCourse.ifPresentOrElse(course -> {
			
			course.getStudents().stream().forEach(student ->{
				student.getCourses().remove(course);
				Student studentDb = studentRepository.save(student);
				System.out.println(studentDb);
				
			});
			
			courseRepository.delete(course);
			
			
		}, ()->System.out.println("Este curso no existe"));		
		
		scanner.close();		
	}
	
	@Transactional
	public void deleteStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion estudiante---------------");
		System.out.println("Ingrese el id del estudiante:");
		Long id = scanner.nextLong();
		
		Optional<Student> optStudent = studentRepository.findById(id);
		
		optStudent.ifPresentOrElse(student -> {
			
			student.getCourses().stream().forEach(course ->{
				course.getStudents().remove(student);
				Course courseDb = courseRepository.save(course);
				System.out.println(courseDb);
				
			});
			
			
			studentRepository.delete(student);
			System.out.println("Estudiante eliminado");
			
			
		}, () -> System.out.println("Estudiante no existe"));
		
		
		scanner.close();
		
	}
	
	@Transactional
	public void deleteCourseFromStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion curso de estudiante---------------");
		System.out.println("Ingrese el id del estudiante:");
		Long id = scanner.nextLong();
		
		Optional<Student> optStudent = studentRepository.findById(id);
		
		optStudent.ifPresentOrElse(student -> {
			System.out.println(student);
			
			System.out.println("Ingrese el id del curso a eliminar:");
			Long idCourse = scanner.nextLong();		
			
			
			boolean available = student.getCourses().stream().anyMatch(c -> c.getId().equals(idCourse));
			
			if(available == true)
			{
				
				Optional<Course> optCourse = courseRepository.findById(idCourse);
				
				optCourse.ifPresentOrElse(course -> {
				
						student.getCourses().remove(course);
						Student studentDb = studentRepository.save(student);
						System.out.println(studentDb);
					
					
					
				}, () -> System.out.println("El curso no existe"));				
				
				
			}else {
				System.out.println("El estudiante no tiene asignado este curso");
				
			}		
			
		}, () -> System.out.println("Estudiante no existe"));
		
		
		scanner.close();
		
	}
	
	
	
	@Transactional
	public void addCourseToStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Creacion estudiante---------------");
		System.out.println("Ingrese el id del estudiante:");
		Long id = scanner.nextLong();
		
		
		Optional<Student> optStudent = studentRepository.findById(id);
		
		optStudent.ifPresentOrElse(student -> {
			System.out.println(student);
			
			List<Course> studentCourses = student.getCourses();
			List<Long> courseIds = new ArrayList<>();
			
			for(Course course: studentCourses) {
				courseIds.add(course.getId());
			}
			
			List<Course> availableCourses = (List<Course>) courseRepository.findByIdNotIn(courseIds);
			System.out.println("----------cursos disponibles---------------");
			availableCourses.forEach(System.out::println);
			
			boolean addMoreCourses = true;
			List<Course> coursesAdded = new ArrayList<>();
			
			while(addMoreCourses == true) {
				
				System.out.println("Ingrese el id del curso deseado:");
				Long idCourse = scanner.nextLong();
				
				boolean available = availableCourses.stream().anyMatch(c -> c.getId().equals(idCourse));
				
				if(available == true) {
					Optional<Course> optCourse = courseRepository.findById(idCourse);
					optCourse.ifPresentOrElse(course -> {
						
						coursesAdded.add(course);
						
					}, ()->System.out.println("No existe un curso con ese Id"));
					
										
				}else {
					System.out.println("Este curso no esta disponible, ya fue tomado por este estudiante");	
					
				}
				
				System.out.println("Desea agregar otro curso(y o n):");
				String addMore = scanner.next();
				
				addMoreCourses = (addMore.equals("y")) ? true : false;
				
			}
			
			coursesAdded.stream().forEach(newCourse -> {				
				student.getCourses().add(newCourse);
			});
			
			Student studentDb = studentRepository.save(student);
			System.out.println(studentDb);		
			
			
		}, ()->System.out.println("estudiante no existe"));
		
		
		scanner.close();
		
	}
	
	
	
	@Transactional
	public void addStudent() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Creacion estudiante---------------");
		System.out.println("Ingrese el nombre del estudiante:");
		String name  = scanner.next();
		
		System.out.println("Ingrese el apellido del estudiante:");
		String lastname  = scanner.next();
		
		Student student = new Student(name,lastname);
		
		Student studentDb = studentRepository.save(student);
		
		System.out.println(studentDb);
		
		scanner.close();
	}
	
	
	@Transactional
	public void addCourse() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Creacion curso---------------");
		System.out.println("Ingrese el nombre del curso:");
		String name  = scanner.next();
		
		System.out.println("Ingrese el contenido del curso:");
		String content  = scanner.next();
		
		System.out.println("Ingrese el nombre del instructor del curso:");
		String instructor  = scanner.next();
		
		Course course = new Course(name,instructor,content);
		
		Course courseDb = courseRepository.save(course);
		
		System.out.println(courseDb);
		
		scanner.close();
		
	}
	
	@Transactional
	public void createClientDetail() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Creacion detalle cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		optClient.ifPresentOrElse(client ->{
			System.out.println("----------El cliente existe---------------");
			System.out.println("Ingrese si el cliente es premium(y o n):");
			String Premium = scanner.next();
			
			boolean isPremium = (Premium.equals("y")) ? true : false;
			
			System.out.println("Ingrese la cantidad de puntos del cliente:");
			int points = scanner.nextInt();
			
			ClientDetail clientDetail = new ClientDetail(isPremium,points);
			//clientDetail.setClient(client);
			//clientDetailRepository.save(clientDetail);
			
			client.setDetails(clientDetail);
			Client clientDB = clientRepository.save(client);

			System.out.println("cliente actualizado correctamente");
			System.out.println(clientDB);
			
		},() -> System.out.println("El cliente no existe"));
		
		
		scanner.close();
		
	}
	
	@Transactional
	public void addAddresesExitingClient()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion de cliente de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		optClient.ifPresentOrElse(client -> {
			
			System.out.println(client);
			List<Address> addresses = new ArrayList<>();
			boolean addAddress = true;

			while (addAddress == true) {
				System.out.println("Ingrese la direccion (calle) del cliente:");
				scanner.nextLine();
				String street = scanner.nextLine();
				System.out.println("Ingrese la direccion (numero) del cliente:");
				Integer number = scanner.nextInt();

				Address address = new Address(street, number);
				addresses.add(address);

				System.out.println("desea ingresar otra direccion(y or n)?:");
				String otherAddress = scanner.next();

				addAddress = (otherAddress.equals("y")) ? true : false;

			}

			client.setAddresses(addresses);

			Client clientDB = clientRepository.save(client);

			System.out.println("cliente actualizado correctamente");
			System.out.println(clientDB);

		}, () -> {
			System.out.println("cliente no existe");
		});
		
		scanner.close();
		
	}
	
	
	
	@Transactional
	public void createClientWithAddress() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Creacion de cliente---------------");
		System.out.println("Ingrese el nombre del cliente:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido del cliente:");
		String lastname = scanner.next();
		
		List<Address> addresses = new ArrayList<>();
		
		boolean addAddress = true;
		
		while(addAddress == true)
		{
			System.out.println("Ingrese la direccion (calle) del cliente:");
			scanner.nextLine();
			String street = scanner.nextLine();
			System.out.println("Ingrese la direccion (numero) del cliente:");
			Integer number = scanner.nextInt();
			
			Address address = new Address(street,number);
			addresses.add(address);
			
			System.out.println("desea ingresar otra direccion(y or n)?:");
			String otherAddress = scanner.next();
			
			addAddress = (otherAddress.equals("y")) ? true : false;
			
		}	
		
		Client client = new Client(name,lastname);
		client.setAddresses(addresses);
		
		Client clientDB = clientRepository.save(client);
		
		System.out.println(clientDB);
		
		scanner.close();
		
	}
	
	
	@Transactional
	public void deleteClientWithAddress() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion de cliente de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		
		Optional<Client> client = clientRepository.findById(id);
		//Optional<Client> client = clientRepository.getClientById(id);
		
		if(client.isPresent())
		{
			clientRepository.deleteById(id);
			System.out.println("Cliente eliminado de forma satisfactoria");
			
		}else {
			System.out.println("el cliente no existe, no se puede eliminar");
		}
			
		
		scanner.close();
	
	}
	
	
	
	@Transactional
	public void deleteAddress() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion de direcciones de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
				
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		optClient.ifPresentOrElse(client -> {

			if (client.getAddresses().size() > 0) {

				System.out.println("Direcciones:\n ");
			
				int i = 0;
				
				for (Address address : client.getAddresses())
				{
					
					System.out.println(" - " + address.toString());
					i++;

				}
				
				System.out.println("Indique cual direccion desea eliminar:");
				Long idAddress = scanner.nextLong();
								
				Optional<Address> optAddress = addressRepository.findById(idAddress);
				
				optAddress.ifPresentOrElse(address -> {
					client.getAddresses().remove(address);			
					Client clientDB = clientRepository.save(client);				
					System.out.println("direccion eliminada correctamente");				
					System.out.println(clientDB);
					
				},() -> System.out.println("la opcion seleccionada no existe "));	
			} else {
				System.out.println("cliente no tiene direcciones");
			}

		}, () -> {
			System.out.println("cliente no existe");
		});			
		
		scanner.close();
	
	}
	
	@Transactional
	public void deleteInvoice() { //en el set no funciona la eliminacion por indice
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Eliminacion de facturas de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
				
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		optClient.ifPresentOrElse(client -> {

			if (client.getInvoices().size() > 0) {

				System.out.println("Facturas:\n ");				
				
				for (Invoice invoice : client.getInvoices())
				{
					System.out.println(" - " +  invoice.toString());			

				}
				
				System.out.println("Indique el id de la factura que desea eliminar:");
				Long idInvoice = scanner.nextLong();
				
				Optional<Invoice> optInvoice = invoiceRepository.findById(idInvoice);
				
				optInvoice.ifPresentOrElse(invoice -> {
					client.getInvoices().remove(invoice);			
					Client clientDB = clientRepository.save(client);				
					System.out.println("factura eliminada correctamente");				
					System.out.println(clientDB);
					
				},() -> System.out.println("la opcion seleccionada no existe "));				
				
			} else {
				System.out.println("cliente no tiene facturas");
			}

		}, () -> {
			System.out.println("cliente no existe");
		});			
		
		scanner.close();
	
	}
	
	
	@Transactional
	public void manyToOne() {
		
		Client client = new Client("John","Doe");
		clientRepository.save(client);
		
		Invoice invoice = new Invoice("Compras de oficina",220000l);
		invoice.setClient(client);
		
		Invoice invoiceDB = invoiceRepository.save(invoice);
		
		System.out.println(invoiceDB);
		
	}

	
	@Transactional
	public void findClientById() {
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Busqueda de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);		
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		/*optClient.ifPresentOrElse(c -> {
				System.out.println(c);
			}, () -> {
				System.out.println("cliente no existe");
		});*/
		
		if(optClient.isPresent()) {
			Client client = optClient.orElseThrow();
			System.out.println(client);
		}else {
			System.out.println("cliente no existe");
		}
		
		scanner.close();
	
	}
	
	@Transactional
	public void findClientByIdWithAddresses() {
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Busqueda de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		optClient.ifPresent(client ->{
			client.getAddresses().forEach( address -> {
				System.out.println(address);
			});	
			
		});
		
		scanner.close();
	
	}
	
	@Transactional
	public void createInvoice() {		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Busqueda de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);
		//Optional<Client> optClient = clientRepository.getClientById(id);
		
		if(optClient.isPresent()) {
			
			boolean addInvoices = true;
					
			while(addInvoices == true) {		
			
				Client client = optClient.orElseThrow();
				System.out.println(client);
				System.out.println("----------Creacion de factura---------------");
				System.out.println("Ingrese la descripcion de la factura:");
	 			scanner.nextLine();
	 			String description = scanner.nextLine();
	 			System.out.println("Ingrese el total de la factura:");
	 			Long total = scanner.nextLong();
				
				Invoice invoice = new Invoice(description,total);
				invoice.setClient(client);
				
				Invoice invoiceDB = invoiceRepository.save(invoice);
				System.out.println("Factura creada");
				System.out.println(invoiceDB);
				
				System.out.println("Desea agregar otra factura? (y o n):");
	 			String otherInvoice = scanner.next();
	 			
	 			addInvoices = (otherInvoice.equals("y")) ? true : false;
				
			}
			
		}else {
			System.out.println("Cliente no existe");
		}
		
		scanner.close();
		
	}
}
