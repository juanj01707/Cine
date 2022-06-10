import co.edu.uco.crosscuting.util.object.UtilObject;
import co.edu.uco.grade.dto.CourseDTO;
import co.edu.uco.grades.busineelogic.business.CourseBusiness;
import co.edu.uco.grades.crosscutting.exception.GradesException;
import co.edu.uco.grades.data.factory.DAOFactory;

public class CourseBusinessImpl implements CourseBusiness{
	
	private DAOFactory daoFactory;

	public CourseBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw GradesException.buildTechnicalBusinessLogicException(
					"It's no possible create a CourseBusinessImpl whem the DAOfactory is null");
		}

	}
	@Override
	public void create(CourseDTO dto) {
		daoFactory.getCourseDAO().create(dto);
	}
	@Override
	public void update(CourseDTO dto) {
		daoFactory.getCourseDAO().update(dto);		
	}

	@Override
	public void delete(int id) {
		daoFactory.getCourseDAO().delete(id);		
	}

	@Override
	public void find(CourseDTO dto) {
		daoFactory.getCourseDAO().find(dto);		
	}
}