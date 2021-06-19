package com.example.iherb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.iherb.db.dao.AchievementConditionDao;
import com.example.iherb.db.dao.AchievementDao;
import com.example.iherb.db.dao.ClassificationDao;
import com.example.iherb.db.dao.ContraindicationDao;
import com.example.iherb.db.dao.ContraindicationPillDao;
import com.example.iherb.db.dao.CourseDao;
import com.example.iherb.db.dao.CoursePillDao;
import com.example.iherb.db.dao.CourseUserDao;
import com.example.iherb.db.dao.EffectDao;
import com.example.iherb.db.dao.EffectPillDao;
import com.example.iherb.db.dao.HistoryDao;
import com.example.iherb.db.dao.ParamDao;
import com.example.iherb.db.dao.ParamValueDao;
import com.example.iherb.db.dao.PillDao;
import com.example.iherb.db.dao.UsePillDao;
import com.example.iherb.db.dao.UserAchievementDao;
import com.example.iherb.db.dao.UserDao;
import com.example.iherb.db.database.TableResolver;
import com.example.iherb.db.entities.Achievement;
import com.example.iherb.db.entities.AchievementCondition;
import com.example.iherb.db.entities.Classification;
import com.example.iherb.db.entities.Contraindication;
import com.example.iherb.db.entities.ContraindicationPill;
import com.example.iherb.db.entities.Course;
import com.example.iherb.db.entities.CoursePill;
import com.example.iherb.db.entities.CourseUser;
import com.example.iherb.db.entities.Effect;
import com.example.iherb.db.entities.EffectPill;
import com.example.iherb.db.entities.History;
import com.example.iherb.db.entities.Param;
import com.example.iherb.db.entities.ParamValue;
import com.example.iherb.db.entities.Pill;
import com.example.iherb.db.entities.UsePill;
import com.example.iherb.db.entities.User;
import com.example.iherb.db.entities.UserAchievement;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    //имя файла базы данных который будет храниться в /data/data/APPNAME/DATABASE_NAME.db
    private static final String DATABASE_NAME ="iHerb.db";

    //с каждым увеличением версии, при нахождении в устройстве БД с предыдущей версией будет выполнен метод onUpgrade();
    private static final int DATABASE_VERSION = 1;

    //ссылки на DAO соответсвующие сущностям, хранимым в БД
    private AchievementConditionDao achievementConditionDao = null;
    private ClassificationDao classificationDao = null;
    private EffectDao effectDao = null;
    private HistoryDao historyDao = null;
    private ParamDao paramDao = null;
    private ParamValueDao paramValueDao = null;
    private PillDao pillDao = null;
    private UsePillDao usePillDao = null;
    private UserDao userDao = null;
    private AchievementDao achievementDao = null;
    private UserAchievementDao userAchievementDao = null;
    private ContraindicationDao contraindicationDao = null;
    private ContraindicationPillDao contraindicationPillDao = null;
    private EffectPillDao effectPillDao = null;
    private CourseDao courseDao = null;
    private CoursePillDao coursePillDao = null;
    private CourseUserDao courseUserDao = null;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public CoursePillDao getCoursePillDao() {
        return coursePillDao;
    }

    public void setCoursePillDao(CoursePillDao coursePillDao) {
        this.coursePillDao = coursePillDao;
    }

    public CourseUserDao getCourseUserDao() {
        return courseUserDao;
    }

    public void setCourseUserDao(CourseUserDao courseUserDao) {
        this.courseUserDao = courseUserDao;
    }

    private SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableResolver.getSqlCreateTableString(Achievement.class));
        db.execSQL(TableResolver.getSqlCreateTableString(AchievementCondition.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Classification.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Contraindication.class));
        db.execSQL(TableResolver.getSqlCreateTableString(ContraindicationPill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Effect.class));
        db.execSQL(TableResolver.getSqlCreateTableString(EffectPill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(History.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Param.class));
        db.execSQL(TableResolver.getSqlCreateTableString(ParamValue.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Pill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(UsePill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(User.class));
        db.execSQL(TableResolver.getSqlCreateTableString(UserAchievement.class));
        db.execSQL(TableResolver.getSqlCreateTableString(Course.class));
        db.execSQL(TableResolver.getSqlCreateTableString(CoursePill.class));
        db.execSQL(TableResolver.getSqlCreateTableString(CourseUser.class));

    }

    public void addStartValues(){
        List<Param> paramList = new ArrayList<>();
        paramList.add(new Param("weight", "user weight"));
        paramList.add(new Param("pulse", "user pulse"));
        paramList.add(new Param("height", "user height"));

        List<Classification> classificationList = new ArrayList<>();
        classificationList.add(new Classification("нутрицевтики", "комбинированные средства, прием которых показан при дефиците полезных веществ и спровоцированных им сбоях в работе организма, а также для ускорения эвакуации чужеродных органических и неорганических соединений"));
        classificationList.add(new Classification("парафармацевтики", "биоактивные добавки, используемые для повышения умственной и физической работоспособности, укрепления иммунитета"));
        classificationList.add(new Classification("эубиотики", "БАД, содержащие живые культуры бактерий и (или) питательный субстрат для них, применяемые для восстановления микробиоценоза кишечника и тд."));

        classificationList.add(new Classification("пробиотики", "бактерии, которые доказанно приносят пользу организму человека. Наиболее распространенные пробиотики — лакто- и бифидобактерии."));
        classificationList.add(new Classification("витамины", "особые вещества, необходимые организму для полноценного функционирования. Они не обладают пищевой ценностью, но выполняют важнейшие функции – регулируют обменные процессы, катализируют биохимические реакции, помогают в усвоении многих минеральных компонентов."));

        List<Pill> pillList = new ArrayList<Pill>();

        //иммунитет
        /*1*/pillList.add(new Pill("California Gold Nutrition, LactoBif", "Пробиотик Lactobif от California Gold Nutrition содержит 8 активных и клинически изученных штаммов пробиотиков (5 видов лактобактерий и 3 вида бифидобактерий) на основе эксклюзивного комплекса пробиотиков FloraFIT® от Danisco®.", 4));
        /*2*/pillList.add(new Pill("California Gold Nutrition, Gold C","", 5));
        /*3*/pillList.add(new Pill("California Gold Nutrition, витамин D3", "Капсулы из рыбьего желатина. Поддерживает здоровье костей и зубов. Способствует здоровью иммунной системы. Продукт не содержит глютена, ГМО и сои. Производится на предприятии, которое имеет регистрацию текущей надлежащей производственной практики (cGMP) и проходит независимые проверки", 5));
        /*4*/pillList.add(new Pill("California Gold Nutrition, витамин D3 в каплях для детей", "California Gold Nutrition, витамин D3 в каплях для детей. Витамин D3 в жидкой форме без спирта для детей. Производится на предприятии, проходящем независимые проверки и имеющем регистрацию текущей надлежащей производственной практики (cGMP)", 5));
        /*5*/pillList.add(new Pill("California Gold Nutrition, LactoBif", "Пробиотики LactoBif от California Gold Nutrition. Доступны с дозировкой 5, 30 и 100 млрд КОЕ на растительную капсулу. Эксклюзивное использование пробиотиков FloraFIT® от Danisco. 8 активных и клинически изученных штаммов пробиотиков. Подходит для вегетарианцев и веганов", 4));

        //кишечник
        /*6*/pillList.add(new Pill("Enzymedica, Digest Basic","Не содержит ГМО. The Enzyme Experts. Жизненно необходимое. Поддерживает здоровье пищеварительной системы. Самый популярный бренд ферментов. Деликатная поддержка пищеварения. Легко проглатываемые капсулы. Пищевая добавка. Веганский и кошерный продукт. Сертификат кошерности Kosher Parve", 5));
        /*7*/pillList.add(new Pill("California Gold Nutrition, LactoBif","Доступны с дозировкой 5, 30 и 100 млрд КОЕ на растительную капсулу. Эксклюзивное использование пробиотиков FloraFIT® от Danisco. 8 активных и клинически изученных штаммов пробиотиков. Упаковано в индивидуальные блистеры с двойным слоем фольги. Подходит для вегетарианцев",4));
        /*8*/pillList.add(new Pill("California Gold Nutrition, LactoBif","Доступны с дозировкой 5, 30 и 100 млрд КОЕ в одной вегетарианской капсуле. На основе эксклюзивного комплекса пробиотиков FloraFIT® от Danisco. 8 активных и клинически изученных штаммов пробиотиков. Подходит для вегетарианцев и веганов",4));
        /*9*/pillList.add(new Pill("Sports Research, пробиотики с отсроченным высвобождением","60 млрд КОЕ. 12 штаммов лакто- & бифидобактерий. Сертификат Non-GMO Project Verified. Certified Vegan: сертифицированный веганский продукт. Без глютена. Качество продукта подтверждено в независимой лаборатории. Пищевая добавка", 4));
        /*10*/pillList.add(new Pill("21st Century, смесь ацидофильных пробиотиков","Запатентованная смесь наиболее полезного известного штамма ацидофильных лактобактерий (L. acidophilus), синергически объединенная с тремя другими генетически различными пробиотическими организмами и пребиотическим инулином, предназначенная для поддержания пробиотической активности в кишечнике.", 4));

        //органы дыхания
        /*11*/pillList.add(new Pill("Natural Factors, здоровье легких, бронхов и носа", "Препарат для здоровья легких, бронхов и носовых пазух представляет собой полностью натуральную формулу, разработанную для питания и поддержки всей дыхательной системы.", 1));
        /*12*/pillList.add(new Pill("Natural Factors, Здоровье дыхательных путей (Lung, Bronchial &amp; Sinus Health)", "Препарат для здоровья легких, бронхов и носовых пазух представляет собой полностью натуральную формулу, разработанную для питания и поддержки всей дыхательной системы.", 1));
        /*13*/pillList.add(new Pill("Enzymedica, MucoStop", "MucoStop™ содержит комплекс ферментов, специально подобранных для облегчения дыхания. Высокоэффективный фермент Mucolase™ поддерживает естественную способность организма расщеплять и выводить излишки слизи из носовых пазух и носовых ходов.", 1));
        /*14*/pillList.add(new Pill("Exploding Buds, кордицепс военный, грибной порошок", "Органические грибы Exploding Buds™ полного спектра действия выращиваются, сушатся, измельчаются и упаковываются на современной грибной ферме, зарегистрированной в Южной Калифорнии в соответствии с текущей надлежащей производственной практикой.", 3));
        /*15*/pillList.add(new Pill("Solaray, Коровяк", "Коровяк - представитель семейства фигуристых с опушенными листьями и высокими желтыми шипами цветов. Он происходит из Европы, где использовался с древних времен. Листья коровяка содержат большое количество растворимой клетчатки, известной как слизь, густое студенистое вещество.", 3));

        //снижение веса
        /*16*/pillList.add(new Pill("Gummiology, жевательные таблетки с яблочным уксусом, для взрослых, с натуральным яблочным вкусом", "Жевательные таблетки с яблочным уксусом для взрослых от Gummiology  Путем естественной ферментации яблок производится яблочный уксус, затем к нему добавляется корень имбиря.", 2));
        /*17*/pillList.add(new Pill("California Gold Nutrition, органическое масло", "Среднецепочечные триглицериды, или МСТ, представляют собой питательные жиры, которые организм может быстро сжигать для выработки энергии. Кокосовое масло является здоровым выбором для поддержки MCT и содержит каприловую и каприновую кислоты, два типа среднецепочечных триглицеридов, которые организм может преобразовывать в кетоны и использовать в качестве энергии.", 2));
        /*18*/pillList.add(new Pill("Lake Avenue Nutrition, 5-гидрокситриптофан с витаминами B6 и C", "Способствует улучшению качества сна и поддержанию психического здоровья. Не содержит ГМО, сои и глютена. Производится на предприятии, имеющем регистрацию надлежащей производственной практики (GMP)", 5));
        /*19*/pillList.add(new Pill("Nature's Plus, Spiru-Tein", "Spiru-Tein — научно разработанный продукт с изолятами соевого протеина, получаемыми только из бобов генетически немодифицированной сои (без ГМО) — так, как создала природа.", 2));
        /*20*/pillList.add(new Pill("EVLution Nutrition, LeanMode", "Контроль веса. Управление метаболизмом. Поддержку аппетита. Насыщение антиоксидантами. Формирование фигуры. Нормальный уровень энергии", 2));

        //сон
        /*21*/pillList.add(new Pill("Life Extension, Магний в капсулах","Магний поддерживает здоровье сердечно-сосудистой системы и костей, способствует энергетическому обмену и работе мозга, а также помогает поддерживать нормальную работу нервной системы и здоровое артериальное давление.", 5));
        /*22*/pillList.add(new Pill("Thorne Research, Magnesium Citramate","Магний необходим для выработки энергии, нормализует сердечный ритм, способствует здоровью легких, регулирует уровень глюкозы в крови. Фактически магний участвует в 600 ферментативных процессах в нашем организме.", 5));
        /*23*/pillList.add(new Pill("Doctor's Best, полностью хелатированный легкоусвояемый магний","В легкоусвояемом продукте от Doctor's Best используется запатентованная органическая хелатная форма магния для повышения биодоступности и желудочно-кишечной переносимости. Он не содержит буферных веществ и усваивается легче, чем оксид магния.", 1));
        /*24*/pillList.add(new Pill("KAL, глицинат магния","Магний обеспечивает питательную поддержку нормальной и здоровой функции мышц. Вегетарианское средство.", 5));
        /*25*/pillList.add(new Pill("21st Century, магний","Поддержка здоровья костей и мышц. Минеральная добавка. Не содержит глютена. Гарантированное качество — продукт прошел лабораторные испытания. Магний — это незаменимый минерал, который поддерживает здоровье костей и мышц.", 5));


        List<Effect> effectList = new ArrayList<>();

        /*1*/effectList.add(new Effect("Дозировка","Превышение дозировки","negative"));
        /*2*/effectList.add(new Effect("Беременность","Употребеление во время беременности","negative"));
        /*3*/effectList.add(new Effect("Планирование беременности","Употребеление перед беременностью","negative"));
        /*4*/effectList.add(new Effect("Кормление грудью","Употребеление во время кормления грудью","negative"));
        /*5*/effectList.add(new Effect("Приём медикаментов","Употребление во время приём других медикаметов","negative"));
        /*6*/effectList.add(new Effect("Подготовка к операции","Употребление во время подготовки к операции","negative"));
        /*7*/effectList.add(new Effect("Наличие несовместимых заболеваний","Употребление при наличии несовместимых заболеваний","negative"));
        /*8*/effectList.add(new Effect("Здоровье костей","Способствует здоровью костей","positive"));
        /*9*/effectList.add(new Effect("Здоровье сердца","Способствует здоровью сердца","positive"));
        /*10*/effectList.add(new Effect("Здоровье нервной системы","Способствует здоровью нервной системы","positive"));
        /*11*/effectList.add(new Effect("Здоровье мышц","Способствует здоровью мышц","positive"));
        /*12*/effectList.add(new Effect("Поддержание магния","Способствует поддержанию нормального уровня магния","positive"));
        /*13*/effectList.add(new Effect("Усвояемость","Быстре усвоение организмом ","positive"));
        /*14*/effectList.add(new Effect("Планирование медицинских процедур","Употребление перед медицинскими процедурами","negative"));
        /*15*/effectList.add(new Effect("Только для взрослых","Запрещено для детей","negative"));
        /*16*/effectList.add(new Effect("Хранение в холодильнике","Для хранения требуется холодильник","negative"));
        /*17*/effectList.add(new Effect("Консультация с врачом","Перед применением следует проконсультироваться с врачом","negative"));
        /*18*/effectList.add(new Effect("Отпуск без рецепта","Отпуск без рецепта","positive"));


        List<EffectPill> epList = new ArrayList<>();

        epList.add(new EffectPill(1, 18));

        epList.add(new EffectPill(2, 17));

        epList.add(new EffectPill(3, 2));
        epList.add(new EffectPill(3, 3));
        epList.add(new EffectPill(3, 4));
        epList.add(new EffectPill(3, 5));
        epList.add(new EffectPill(3, 7));
        epList.add(new EffectPill(3, 17));

        epList.add(new EffectPill(4, 17));

        epList.add(new EffectPill(5, 18));

        epList.add(new EffectPill(6, 15));
        epList.add(new EffectPill(6, 18));

        epList.add(new EffectPill(7, 18));

        epList.add(new EffectPill(8, 18));

        epList.add(new EffectPill(9, 17));
        epList.add(new EffectPill(9, 1));
        epList.add(new EffectPill(9, 16));

        epList.add(new EffectPill(10, 1));
        epList.add(new EffectPill(10, 13));
        epList.add(new EffectPill(10, 17));

        epList.add(new EffectPill(11, 1));
        epList.add(new EffectPill(11, 2));
        epList.add(new EffectPill(11, 3));
        epList.add(new EffectPill(11, 4));
        epList.add(new EffectPill(11, 5));
        epList.add(new EffectPill(11, 6));
        epList.add(new EffectPill(11, 7));
        epList.add(new EffectPill(11, 13));

        epList.add(new EffectPill(12, 1));
        epList.add(new EffectPill(12, 2));
        epList.add(new EffectPill(12, 3));
        epList.add(new EffectPill(12, 4));
        epList.add(new EffectPill(12, 5));
        epList.add(new EffectPill(12, 6));
        epList.add(new EffectPill(12, 7));
        epList.add(new EffectPill(12, 17));

        epList.add(new EffectPill(13, 18));

        epList.add(new EffectPill(14, 17));

        epList.add(new EffectPill(15, 16));
        epList.add(new EffectPill(15, 17));
        epList.add(new EffectPill(15, 7));
        epList.add(new EffectPill(15, 15));

        epList.add(new EffectPill(16, 18));
        epList.add(new EffectPill(16, 15));

        epList.add(new EffectPill(17, 1));
        epList.add(new EffectPill(17, 2));
        epList.add(new EffectPill(17, 3));
        epList.add(new EffectPill(17, 4));
        epList.add(new EffectPill(17, 5));
        epList.add(new EffectPill(17, 6));
        epList.add(new EffectPill(17, 7));
        epList.add(new EffectPill(17, 15));
        epList.add(new EffectPill(17, 17));

        epList.add(new EffectPill(18, 13));
        epList.add(new EffectPill(18, 15));
        epList.add(new EffectPill(18, 17));

        epList.add(new EffectPill(19, 1));
        epList.add(new EffectPill(19, 2));
        epList.add(new EffectPill(19, 3));
        epList.add(new EffectPill(19, 4));
        epList.add(new EffectPill(19, 5));
        epList.add(new EffectPill(19, 6));
        epList.add(new EffectPill(19, 7));
        epList.add(new EffectPill(19, 8));
        epList.add(new EffectPill(19, 9));
        epList.add(new EffectPill(19, 10));
        epList.add(new EffectPill(19, 8));

        epList.add(new EffectPill(20, 1));
        epList.add(new EffectPill(20, 2));
        epList.add(new EffectPill(20, 3));
        epList.add(new EffectPill(20, 4));
        epList.add(new EffectPill(20, 5));
        epList.add(new EffectPill(20, 6));
        epList.add(new EffectPill(20, 7));
        epList.add(new EffectPill(20, 15));
        epList.add(new EffectPill(20, 17));

        epList.add(new EffectPill(21, 10));
        epList.add(new EffectPill(21, 11));
        epList.add(new EffectPill(21, 12));
        epList.add(new EffectPill(21, 13));
        epList.add(new EffectPill(21, 14));

        epList.add(new EffectPill(22, 17));

        epList.add(new EffectPill(23, 1));
        epList.add(new EffectPill(23, 2));
        epList.add(new EffectPill(23, 3));
        epList.add(new EffectPill(23, 4));
        epList.add(new EffectPill(23, 5));
        epList.add(new EffectPill(23, 6));
        epList.add(new EffectPill(23, 7));
        epList.add(new EffectPill(23, 8));
        epList.add(new EffectPill(23, 9));
        epList.add(new EffectPill(23, 10));
        epList.add(new EffectPill(23, 11));
        epList.add(new EffectPill(23, 12));
        epList.add(new EffectPill(23, 13));
        epList.add(new EffectPill(23, 18));

        epList.add(new EffectPill(24, 16));
        epList.add(new EffectPill(24, 18));

        epList.add(new EffectPill(25, 1));
        epList.add(new EffectPill(25, 2));
        epList.add(new EffectPill(25, 3));
        epList.add(new EffectPill(25, 4));
        epList.add(new EffectPill(25, 5));
        epList.add(new EffectPill(25, 6));
        epList.add(new EffectPill(25, 7));
        epList.add(new EffectPill(25, 8));
        epList.add(new EffectPill(25, 17));








        for (Param param : paramList)
            getParamDao().create(param);

        for (Classification classification : classificationList){
            getClassificationDao().create(classification);
        }

        for (Pill pill : pillList){
            getPillDao().create(pill);
        }

        for (Effect effect : effectList){
            getEffectDao().create(effect);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public AchievementConditionDao getAchievementConditionDao() {
        return achievementConditionDao;
    }

    public void setAchievementConditionDao(AchievementConditionDao achievementConditionDao) {
        this.achievementConditionDao = achievementConditionDao;
    }

    public ClassificationDao getClassificationDao() {
        return classificationDao;
    }

    public void setClassificationDao(ClassificationDao classificationDao) {
        this.classificationDao = classificationDao;
    }

    public EffectDao getEffectDao() {
        return effectDao;
    }

    public void setEffectDao(EffectDao effectDao) {
        this.effectDao = effectDao;
    }

    public HistoryDao getHistoryDao() {
        return historyDao;
    }

    public void setHistoryDao(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public ParamDao getParamDao() {
        return paramDao;
    }

    public void setParamDao(ParamDao paramDao) {
        this.paramDao = paramDao;
    }

    public ParamValueDao getParamValueDao() {
        return paramValueDao;
    }

    public void setParamValueDao(ParamValueDao paramValueDao) {
        this.paramValueDao = paramValueDao;
    }

    public PillDao getPillDao() {
        return pillDao;
    }

    public void setPillDao(PillDao pillDao) {
        this.pillDao = pillDao;
    }

    public UsePillDao getUsePillDao() {
        return usePillDao;
    }

    public void setUsePillDao(UsePillDao usePillDao) {
        this.usePillDao = usePillDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public AchievementDao getAchievementDao() {
        return achievementDao;
    }

    public void setAchievementDao(AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    public UserAchievementDao getUserAchievementDao() {
        return userAchievementDao;
    }

    public void setUserAchievementDao(UserAchievementDao userAchievementDao) {
        this.userAchievementDao = userAchievementDao;
    }

    public ContraindicationDao getContraindicationDao() {
        return contraindicationDao;
    }

    public void setContraindicationDao(ContraindicationDao contraindicationDao) {
        this.contraindicationDao = contraindicationDao;
    }

    public ContraindicationPillDao getContraindicationPillDao() {
        return contraindicationPillDao;
    }

    public void setContraindicationPillDao(ContraindicationPillDao contraindicationPillDao) {
        this.contraindicationPillDao = contraindicationPillDao;
    }

    public EffectPillDao getEffectPillDao() {
        return effectPillDao;
    }

    public void setEffectPillDao(EffectPillDao effectPillDao) {
        this.effectPillDao = effectPillDao;
    }

    //выполняется при закрытии приложения
    @Override
    public void close(){
        super.close();
        userDao = null;
        achievementDao = null;
        userAchievementDao = null;
        achievementConditionDao = null;
        classificationDao = null;
        effectDao = null;
        historyDao = null;
        paramDao = null;
        paramValueDao = null;
        pillDao = null;
        usePillDao = null;
        contraindicationDao = null;
        contraindicationPillDao = null;
        effectPillDao = null;
        courseDao = null;
        coursePillDao = null;
        courseUserDao = null;

    }


}
