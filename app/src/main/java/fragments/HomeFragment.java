package fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.app.helloar.R;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import models.NewsModel;
import views.MainActivity;
import views.SelectedNews;

public class HomeFragment extends Fragment {
    TextView date;
    CardView diagnosis, latestNews, notes, card1, card2, carouselCard;
    TextView card1_date, card1_title, card1_subTitle;
    TextView card2_date, card2_title, card2_subTitle;
    ImageCarousel carousel;

    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getViews(view);

        // Date
        Calendar calendar = Calendar.getInstance();
        date.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + Calendar.getInstance().get(Calendar.YEAR));

        // Diagnosis Card
        diagnosis.setBackgroundResource(R.drawable.home_gradient);
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.diagnosisFragment);
            }
        });

        // Latest News Card
        latestNews.setBackgroundResource(R.drawable.home_gradient);
        latestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.newsFragment);
            }
        });


        // Notes Card
        notes.setBackgroundResource(R.drawable.home_gradient);

        // Carousel Card
        carouselCard.setBackgroundResource(R.drawable.home_gradient);
        carouselCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.bottomNavigationView.setSelectedItemId(R.id.wikiFragment);
            }
        });

        //Static Card 1 settings
        NewsModel news1 = new NewsModel(
                "1",
                "https://s3.eu-north-1.amazonaws.com/semantix-com/content-images/_articleHeroBelow/medical-translations-1920x860.jpg",
                "Highly creative people have ‘unique brain connectivity,’ study shows",
                "The latest research into creativity compares the brain function of exceptionally creative visual artists and scientists with a highly educated group.",
                "Research into creative brains is not new, but it also is not a field that has a lot of research, especially where exceptionally creative people are concerned.\n" +
                        "\n" +
                        "Researchers at the University of California, Los Angeles (UCLA) wanted to look more into how the brains of extremely creative people function. Rather than making the comparison to the average person’s brain, they wanted to compare that functioning to non-creative people with comparable IQs.\n" +
                        "\n" +
                        "The study was published in Psychology of Aesthetics, Creativity, and the Arts.The researchers compiled two groups of participants for the study. The first group consisted of exceptionally creative artists and scientists who were nominated by experts.\n" +
                        "\n" +
                        "The people in this group, which was labeled “Big C,” included only people who scored in the top 2% of the Creative Achievement Questionnaire (CAQ). According to the American Psychological Association, the CAQ “assesses achievement across 10 domains of creativity.”\n" +
                        "\n" +
                        "These domains are visual arts, music, creative writing, dance, drama, architecture, humor, scientific discovery, invention, and culinary arts.\n" +
                        "\n" +
                        "The other group consisted of people who were not exceptionally creative but were still highly intelligent. The researchers labeled this group the “smart comparison group” (SCG).\n" +
                        "\n" +
                        "The SCG participants were previously involved in another study at UCLA and were matched with people in the Big C group. The two groups were matched on age, sex, race, and estimated IQ.\n" +
                        "\n" +
                        "The researchers used fMRI testing on both groups while they were at rest and while they were engaged in tasks. They studied brain activity in different regions of the brain.",
                "2022-04-29T11:56:33.834Z");


//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTimeInMillis(news1.getDate());
//        card1_date.setText(calendar1.get(Calendar.DAY_OF_MONTH) + " "
//                + calendar1.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " "
//                + calendar1.get(Calendar.YEAR));

        Date dt1 = Date.from(Instant.parse(news1.getDate()));
        card1_date.setText(dt1.toString());

        card1_title.setText(news1.getTitle());
        card1_subTitle.setText(news1.getSubTitle());
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SelectedNews.class);
                intent.putExtra("image", news1.getImage());
                intent.putExtra("title", news1.getTitle());
                intent.putExtra("subTitle", news1.getSubTitle());
                intent.putExtra("body", news1.getBody());
                intent.putExtra("from", "home");
                startActivity(intent);
            }
        });

        // Static Card 2 Settings
        NewsModel news2 = new NewsModel(
                "2",
                "https://www.tuv.com/content-media-files/master-content/services/products/1013-tuv-rheinland-tested-medical-device/shutterstock_335680307_core_2_2_1.jpg",
                "Cervical cancer: Single-dose HPV vaccine 'highly effective'",
                "A new, single-dose human papillomavirus vaccine may help the World Health Organization reach its goal to vaccinate 90% of 15-year-old girls against HPV by 2030.",
                "According to the World Health OrganizationTrusted Source (WHO), in 2020 342,000 women died due to cervical cancer. 90% of these deaths occurred in low- or middle-income countries.\n" +
                        "\n" +
                        "HPV — and in particular its serotypes 16 and 18 — account for 50% of high-grade cervical pre-cancers.\n" +
                        "\n" +
                        "Currently, women and girls can be vaccinated against HPV, but according to the U.S. Centers for Disease Control and PreventionTrusted Source (CDC), this requires a two- or three-dose regimen.\n" +
                        "\n" +
                        "The need for multiple doses slows down the rate at which women and girls can be vaccinated. This is particularly the case in low- or middle-income countries, where there is less infrastructure and less money to enable rapid, widespread vaccination.\n" +
                        "\n" +
                        "Researchers have found that having more people vaccinated reduces the spread of HPV at a population level.\n" +
                        "\n" +
                        "According to WHO Assistant Director-General Dr. Princess Nothemba (Nono) Simelela, the new study’s findings may help with the goal of eliminating cervical cancer.\n" +
                        "\n" +
                        "“I firmly believe the elimination of cervical cancer is possible,” says Dr. Simelela.\n" +
                        "\n" +
                        "“In 2020 the Cervical Cancer Elimination InitiativeTrusted Source was launched to address several challenges including the inequity in vaccine access. This single-dose recommendation has the potential to take us faster to our goal of having 90% of girls vaccinated by the age of 15 by 2030.”",
                "2022-04-29T11:56:33.834Z"
        );


//        Calendar calendar2 = Calendar.getInstance();
//        calendar1.setTimeInMillis(news2.getDate());
//        card2_date.setText(calendar2.get(Calendar.DAY_OF_MONTH) + " "
//                + calendar2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " "
//                + calendar2.get(Calendar.YEAR));
        Date dt2 = Date.from(Instant.parse(news2.getDate()));
        card2_date.setText(dt2.toString());

        card2_title.setText(news2.getTitle());
        card2_subTitle.setText(news2.getSubTitle());
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SelectedNews.class);
                intent.putExtra("image", news2.getImage());
                intent.putExtra("title", news2.getTitle());
                intent.putExtra("subTitle", news2.getSubTitle());
                intent.putExtra("body", news2.getBody());
                intent.putExtra("from", "home");
                startActivity(intent);
            }
        });

        // Carousel Settings
        carousel.registerLifecycle(getViewLifecycleOwner());
        List<CarouselItem> carouselList = new ArrayList<>();
        carouselList.add(new CarouselItem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Heart_anterior_exterior_view.png/800px-Heart_anterior_exterior_view.png",
                "Heart"));
        carouselList.add(new CarouselItem("https://aighospitals.com/wp-content/uploads/2020/05/Centre-of-Liver-Transplant.png",
                "Liver"));
        carouselList.add(new CarouselItem("https://www.brainline.org/sites/all/modules/custom/bl_brain/images/brain-lateral.png",
                "Brain"));
        carousel.setData(carouselList);


        return view;
    }

    private void getViews(View view) {
        diagnosis = view.findViewById(R.id.home_diagnosis);
        latestNews = view.findViewById(R.id.home_latest_news);
        notes = view.findViewById(R.id.home_notes);
        date = view.findViewById(R.id.home_date);
        card1_date = view.findViewById(R.id.home_news1_date);
        card1_title = view.findViewById(R.id.home_news1_title);
        card1_subTitle = view.findViewById(R.id.home_news1_subTitle);
        card2_date = view.findViewById(R.id.home_news2_date);
        card2_title = view.findViewById(R.id.home_news2_title);
        card2_subTitle = view.findViewById(R.id.home_news2_subTitle);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        carousel = view.findViewById(R.id.home_carousel);
        carouselCard = view.findViewById(R.id.home_carousel_card);
    }
}