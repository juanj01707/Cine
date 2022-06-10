package co.movies.businesslogic.business;
import co.movies.crosscutting.util.object.*;
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