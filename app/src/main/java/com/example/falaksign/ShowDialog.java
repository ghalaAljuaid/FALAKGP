package com.example.falaksign;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ShowDialog {

    Context context;

    public ShowDialog(Context context) {
        this.context = context;
    }

    public void showSunDialog() {

        List<Aya> ayaList = new ArrayList<>();
        ayaList.add(new Aya(
                "فَلَمَّا رَأَى الشَّمْسَ بَازِغَةً قَالَ هَـٰذَا رَبِّي هَـٰذَا أَكْبَرُ ۖ فَلَمَّا أَفَلَتْ قَالَ يَا قَوْمِ إِنِّي بَرِيءٌ مِّمَّا تُشْرِكُونَ ﴿٧٨ الأنعام﴾",
                R.raw.sun_1));
        ayaList.add(new Aya(
                "هُوَ الَّذِي جَعَلَ الشَّمْسَ ضِيَاءً وَالْقَمَرَ نُورًا وَقَدَّرَهُ مَنَازِلَ لِتَعْلَمُوا عَدَدَ السِّنِينَ وَالْحِسَابَ ۚ مَا خَلَقَ اللَّهُ ذَٰلِكَ إِلَّا بِالْحَقِّ ۚ يُفَصِّلُ الْآيَاتِ لِقَوْمٍ يَعْلَمُونَ ﴿٥ يونس﴾",
                R.raw.sun_2));

        ayaList.add(new Aya(
                "اللَّهُ الَّذِي رَفَعَ السَّمَاوَاتِ بِغَيْرِ عَمَدٍ تَرَوْنَهَا ۖ ثُمَّ اسْتَوَىٰ عَلَى الْعَرْشِ ۖ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ ۖ كُلٌّ يَجْرِي لِأَجَلٍ مُّسَمًّى ۚ يُدَبِّرُ الْأَمْرَ يُفَصِّلُ الْآيَاتِ لَعَلَّكُم بِلِقَاءِ رَبِّكُمْ تُوقِنُونَ ﴿٢ الرعد﴾",
                R.raw.sun_3));

        ayaList.add(new Aya(
                "وَسَخَّرَ لَكُمُ الشَّمْسَ وَالْقَمَرَ دَائِبَيْنِ ۖ وَسَخَّرَ لَكُمُ اللَّيْلَ وَالنَّهَارَ ﴿٣٣ ابراهيم﴾",
                R.raw.sun_4));

        ayaList.add(new Aya(
                "أَقِمِ الصَّلَاةَ لِدُلُوكِ الشَّمْسِ إِلَىٰ غَسَقِ اللَّيْلِ وَقُرْآنَ الْفَجْرِ ۖ إِنَّ قُرْآنَ الْفَجْرِ كَانَ مَشْهُودًا ﴿٧٨ الإسراء﴾",
                R.raw.sun_5));
        ayaList.add(new Aya(
                "وَتَرَى الشَّمْسَ إِذَا طَلَعَت تَّزَاوَرُ عَن كَهْفِهِمْ ذَاتَ الْيَمِينِ وَإِذَا غَرَبَت تَّقْرِضُهُمْ ذَاتَ الشِّمَالِ وَهُمْ فِي فَجْوَةٍ مِّنْهُ ۚ ذَٰلِكَ مِنْ آيَاتِ اللَّهِ ۗ مَن يَهْدِ اللَّهُ فَهُوَ الْمُهْتَدِ ۖ وَمَن يُضْلِلْ فَلَن تَجِدَ لَهُ وَلِيًّا مُّرْشِدًا ﴿١٧ الكهف﴾",
                R.raw.sun_6));

        ayaList.add(new Aya(
                "حَتَّىٰ إِذَا بَلَغَ مَغْرِبَ الشَّمْسِ وَجَدَهَا تَغْرُبُ فِي عَيْنٍ حَمِئَةٍ وَوَجَدَ عِندَهَا قَوْمًا ۗ قُلْنَا يَا ذَا الْقَرْنَيْنِ إِمَّا أَن تُعَذِّبَ وَإِمَّا أَن تَتَّخِذَ فِيهِمْ حُسْنًا ﴿٨٦ الكهف﴾",
                R.raw.sun_7));

        ayaList.add(new Aya(
                "حَتَّىٰ إِذَا بَلَغَ مَطْلِعَ الشَّمْسِ وَجَدَهَا تَطْلُعُ عَلَىٰ قَوْمٍ لَّمْ نَجْعَل لَّهُم مِّن دُونِهَا سِتْرًا ﴿٩٠ الكهف﴾",
                R.raw.sun_8));

        ayaList.add(new Aya(
                "فَاصْبِرْ عَلَىٰ مَا يَقُولُونَ وَسَبِّحْ بِحَمْدِ رَبِّكَ قَبْلَ طُلُوعِ الشَّمْسِ وَقَبْلَ غُرُوبِهَا ۖ وَمِنْ آنَاءِ اللَّيْلِ فَسَبِّحْ وَأَطْرَافَ النَّهَارِ لَعَلَّكَ تَرْضَىٰ ﴿١٣٠ طه﴾",
                R.raw.sun_9));

        ayaList.add(new Aya(
                "أَلَمْ تَرَ إِلَىٰ رَبِّكَ كَيْفَ مَدَّ الظِّلَّ وَلَوْ شَاءَ لَجَعَلَهُ سَاكِنًا ثُمَّ جَعَلْنَا الشَّمْسَ عَلَيْهِ دَلِيلًا ﴿٤٥ الفرقان﴾",
                R.raw.sun_10));

        ayaList.add(new Aya(
                "وَلَئِن سَأَلْتَهُم مَّنْ خَلَقَ السَّمَاوَاتِ وَالْأَرْضَ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ لَيَقُولُنَّ اللَّهُ ۖ فَأَنَّىٰ يُؤْفَكُونَ ﴿٦١ العنكبوت﴾",
                R.raw.sun_11));

        ayaList.add(new Aya(
                "أَلَمْ تَرَ أَنَّ اللَّهَ يُولِجُ اللَّيْلَ فِي النَّهَارِ وَيُولِجُ النَّهَارَ فِي اللَّيْلِ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ كُلٌّ يَجْرِي إِلَىٰ أَجَلٍ مُّسَمًّى وَأَنَّ اللَّهَ بِمَا تَعْمَلُونَ خَبِيرٌ ﴿٢٩ لقمان﴾",
                R.raw.sun_12));

        ayaList.add(new Aya(
                "يُولِجُ اللَّيْلَ فِي النَّهَارِ وَيُولِجُ النَّهَارَ فِي اللَّيْلِ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ كُلٌّ يَجْرِي لِأَجَلٍ مُّسَمًّى ۚ ذَٰلِكُمُ اللَّهُ رَبُّكُمْ لَهُ الْمُلْكُ ۚ وَالَّذِينَ تَدْعُونَ مِن دُونِهِ مَا يَمْلِكُونَ مِن قِطْمِيرٍ ﴿١٣ فاطر﴾",
                R.raw.sun_13));

        ayaList.add(new Aya(
                "لَا الشَّمْسُ يَنبَغِي لَهَا أَن تُدْرِكَ الْقَمَرَ وَلَا اللَّيْلُ سَابِقُ النَّهَارِ ۚ وَكُلٌّ فِي فَلَكٍ يَسْبَحُونَ ﴿٤٠ يس﴾",
                R.raw.sun_14));

        ayaList.add(new Aya(
                "خَلَقَ السَّمَاوَاتِ وَالْأَرْضَ بِالْحَقِّ ۖ يُكَوِّرُ اللَّيْلَ عَلَى النَّهَارِ وَيُكَوِّرُ النَّهَارَ عَلَى اللَّيْلِ ۖ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ ۖ كُلٌّ يَجْرِي لِأَجَلٍ مُّسَمًّى ۗ أَلَا هُوَ الْعَزِيزُ الْغَفَّارُ ﴿٥ الزمر﴾",
                R.raw.sun_15));

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dilog_layout, null);
        alertDialog.setView(alertDialogView);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        ImageView cancelIV = dialog.findViewById(R.id.imageView12);
        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        RecyclerView plantesRecyclerView = dialog.findViewById(R.id.plantesRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        plantesRecyclerView.setLayoutManager(manager);

        AyaAdapter adapter = new AyaAdapter(context, ayaList);
        plantesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void showMoonDialog() {

        List<Aya> ayaList = new ArrayList<>();
        ayaList.add(new Aya("﴿وَخَسَفَ الْقَمَرُ﴾",
                R.raw.moon_1));
        ayaList.add(new Aya("فَالِقُ الْإِصْبَاحِ وَجَعَلَ اللَّيْلَ سَكَنًا وَالشَّمْسَ وَالْقَمَرَ حُسْبَانًا ۚ ذَٰلِكَ تَقْدِيرُ الْعَزِيزِ الْعَلِيمِ ﴿٩٦ الأنعام﴾",
                R.raw.moon_2));
        ayaList.add(new Aya("لَا الشَّمْسُ يَنبَغِي لَهَا أَن تُدْرِكَ الْقَمَرَ وَلَا اللَّيْلُ سَابِقُ النَّهَارِ ۚ وَكُلٌّ فِي فَلَكٍ يَسْبَحُونَ ﴿٤٠ يس﴾",
                R.raw.moon_3));
        ayaList.add(new Aya("اللَّهُ الَّذِي رَفَعَ السَّمَاوَاتِ بِغَيْرِ عَمَدٍ تَرَوْنَهَا ۖ ثُمَّ اسْتَوَىٰ عَلَى الْعَرْشِ ۖ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ ۖ كُلٌّ يَجْرِي لِأَجَلٍ مُّسَمًّى ۚ يُدَبِّرُ الْأَمْرَ يُفَصِّلُ الْآيَاتِ لَعَلَّكُم بِلِقَاءِ رَبِّكُمْ تُوقِنُونَ ﴿٢ الرعد﴾",
                R.raw.moon_4));
        ayaList.add(new Aya("إِنَّ رَبَّكُمُ اللَّهُ الَّذِي خَلَقَ السَّمَاوَاتِ وَالْأَرْضَ فِي سِتَّةِ أَيَّامٍ ثُمَّ اسْتَوَىٰ عَلَى الْعَرْشِ يُغْشِي اللَّيْلَ النَّهَارَ يَطْلُبُهُ حَثِيثًا وَالشَّمْسَ وَالْقَمَرَ وَالنُّجُومَ مُسَخَّرَاتٍ بِأَمْرِهِ ۗ أَلَا لَهُ الْخَلْقُ وَالْأَمْرُ ۗ تَبَارَكَ اللَّهُ رَبُّ الْعَالَمِينَ ﴿٥٤ الأعراف﴾",
                R.raw.moon_5));
        ayaList.add(new Aya("هُوَ الَّذِي جَعَلَ الشَّمْسَ ضِيَاءً وَالْقَمَرَ نُورًا وَقَدَّرَهُ مَنَازِلَ لِتَعْلَمُوا عَدَدَ السِّنِينَ وَالْحِسَابَ ۚ مَا خَلَقَ اللَّهُ ذَٰلِكَ إِلَّا بِالْحَقِّ ۚ يُفَصِّلُ الْآيَاتِ لِقَوْمٍ يَعْلَمُونَ ﴿٥ يونس﴾",
                R.raw.moon_6));
        ayaList.add(new Aya("وَالْقَمَرَ قَدَّرْنَاهُ مَنَازِلَ حَتَّىٰ عَادَ كَالْعُرْجُونِ الْقَدِيمِ ﴿٣٩ يس﴾",
                R.raw.moon_7));
        ayaList.add(new Aya("إِذْ قَالَ يُوسُفُ لِأَبِيهِ يَا أَبَتِ إِنِّي رَأَيْتُ أَحَدَ عَشَرَ كَوْكَبًا وَالشَّمْسَ وَالْقَمَرَ رَأَيْتُهُمْ لِي سَاجِدِينَ ﴿٤ يوسف﴾",
                R.raw.moon_8));
        ayaList.add(new Aya("وَسَخَّرَ لَكُمُ الشَّمْسَ وَالْقَمَرَ دَائِبَيْنِ ۖ وَسَخَّرَ لَكُمُ اللَّيْلَ وَالنَّهَارَ ﴿٣٣ ابراهيم﴾",
                R.raw.moon_9));
        ayaList.add(new Aya("وَسَخَّرَ لَكُمُ اللَّيْلَ وَالنَّهَارَ وَالشَّمْسَ وَالْقَمَرَ ۖ وَالنُّجُومُ مُسَخَّرَاتٌ بِأَمْرِهِ ۗ إِنَّ فِي ذَٰلِكَ لَآيَاتٍ لِّقَوْمٍ يَعْقِلُونَ ﴿١٢ النحل﴾",
                R.raw.moon_10));
        ayaList.add(new Aya("أَلَمْ تَرَ أَنَّ اللَّهَ يَسْجُدُ لَهُ مَن فِي السَّمَاوَاتِ وَمَن فِي الْأَرْضِ وَالشَّمْسُ وَالْقَمَرُ وَالنُّجُومُ وَالْجِبَالُ وَالشَّجَرُ وَالدَّوَابُّ وَكَثِيرٌ مِّنَ النَّاسِ ۖ وَكَثِيرٌ حَقَّ عَلَيْهِ الْعَذَابُ ۗ وَمَن يُهِنِ اللَّهُ فَمَا لَهُ مِن مُّكْرِمٍ ۚ إِنَّ اللَّهَ يَفْعَلُ مَا يَشَاءُ ﴿١٨ الحج﴾",
                R.raw.moon_11));
        ayaList.add(new Aya("وَهُوَ الَّذِي خَلَقَ اللَّيْلَ وَالنَّهَارَ وَالشَّمْسَ وَالْقَمَرَ ۖ كُلٌّ فِي فَلَكٍ يَسْبَحُونَ ﴿٣٣ الأنبياء﴾",
                R.raw.moon_12));
        ayaList.add(new Aya("وَلَئِن سَأَلْتَهُم مَّنْ خَلَقَ السَّمَاوَاتِ وَالْأَرْضَ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ لَيَقُولُنَّ اللَّهُ ۖ فَأَنَّىٰ يُؤْفَكُونَ ﴿٦١ العنكبوت﴾",
                R.raw.moon_13));
        ayaList.add(new Aya("أَلَمْ تَرَ أَنَّ اللَّهَ يُولِجُ اللَّيْلَ فِي النَّهَارِ وَيُولِجُ النَّهَارَ فِي اللَّيْلِ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ كُلٌّ يَجْرِي إِلَىٰ أَجَلٍ مُّسَمًّى وَأَنَّ اللَّهَ بِمَا تَعْمَلُونَ خَبِيرٌ ﴿٢٩ لقمان﴾",
                R.raw.moon_14));
        ayaList.add(new Aya("يُولِجُ اللَّيْلَ فِي النَّهَارِ وَيُولِجُ النَّهَارَ فِي اللَّيْلِ وَسَخَّرَ الشَّمْسَ وَالْقَمَرَ كُلٌّ يَجْرِي لِأَجَلٍ مُّسَمًّى ۚ ذَٰلِكُمُ اللَّهُ رَبُّكُمْ لَهُ الْمُلْكُ ۚ وَالَّذِينَ تَدْعُونَ مِن دُونِهِ مَا يَمْلِكُونَ مِن قِطْمِيرٍ ﴿١٣ فاطر﴾",
                R.raw.moon_15));

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dilog_layout, null);
        alertDialog.setView(alertDialogView);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        ImageView cancelIV = dialog.findViewById(R.id.imageView12);
        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        RecyclerView plantesRecyclerView = dialog.findViewById(R.id.plantesRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        plantesRecyclerView.setLayoutManager(manager);

        AyaAdapter adapter = new AyaAdapter(context, ayaList);
        plantesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void showPlanetsDialog() {

        List<Aya> ayaList = new ArrayList<>();
        ayaList.add(new Aya("إِنَّا زَيَّنَّا السَّمَاءَ الدُّنْيَا بِزِينَةٍ الْكَوَاكِبِ ﴿٦ الصافات﴾",
                R.raw.planets_1));
        ayaList.add(new Aya("وَإِذَا الْكَوَاكِبُ انتَثَرَتْ ﴿٢ الإنفطار﴾",
                R.raw.planets_2));
        ayaList.add(new Aya("فَلَمَّا جَنَّ عَلَيْهِ اللَّيْلُ رَأَىٰ كَوْكَبًا ۖ قَالَ هَـٰذَا رَبِّي ۖ فَلَمَّا أَفَلَ قَالَ لَا أُحِبُّ الْآفِلِينَ ﴿٧٦ الأنعام﴾",
                R.raw.planets_3));
        ayaList.add(new Aya("إِذْ قَالَ يُوسُفُ لِأَبِيهِ يَا أَبَتِ إِنِّي رَأَيْتُ أَحَدَ عَشَرَ كَوْكَبًا وَالشَّمْسَ وَالْقَمَرَ رَأَيْتُهُمْ لِي سَاجِدِينَ ﴿٤ يوسف﴾",
                R.raw.planets_4));
        ayaList.add(new Aya("اللَّهُ نُورُ السَّمَاوَاتِ وَالْأَرْضِ ۚ مَثَلُ نُورِهِ كَمِشْكَاةٍ فِيهَا مِصْبَاحٌ ۖ الْمِصْبَاحُ فِي زُجَاجَةٍ ۖ الزُّجَاجَةُ كَأَنَّهَا كَوْكَبٌ دُرِّيٌّ يُوقَدُ مِنْ شَجَرَةٍ مُبَارَكَةٍ زَيْتُونَةٍ لَا شَرْقِيَّةٍ وَلَا غَرْبِيَّةٍ يَكَادُ زَيْتُهَا يُضِيءُ وَلَوْ لَمْ تَمْسَسْهُ نَارٌ ۚ نُورٌ عَلَى نُورٍ ۗ يَهْدِي اللَّهُ لِنُورِهِ مَنْ يَشَاءُ ۚ وَيَضْرِبُ اللَّهُ الْأَمْثَالَ لِلنَّاسِ ۗ وَاللَّهُ بِكُلِّ شَيْءٍ عَلِيمٌ﴿٣٥ النور﴾",
                R.raw.planets_5));

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dilog_layout, null);
        alertDialog.setView(alertDialogView);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        ImageView cancelIV = dialog.findViewById(R.id.imageView12);
        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        RecyclerView plantesRecyclerView = dialog.findViewById(R.id.plantesRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        plantesRecyclerView.setLayoutManager(manager);

        AyaAdapter adapter = new AyaAdapter(context, ayaList);
        plantesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void showStarsDialog() {

        List<Aya> ayaList = new ArrayList<>();
        ayaList.add(new Aya("النَّجْمُ الثَّاقِبُ ﴿٣ الطارق﴾",
                R.raw.stars_1));
        ayaList.add(new Aya("وَهُوَ الَّذِي جَعَلَ لَكُمُ النُّجُومَ لِتَهْتَدُوا بِهَا فِي ظُلُمَاتِ الْبَرِّ وَالْبَحْرِ ۗ قَدْ فَصَّلْنَا الْآيَاتِ لِقَوْمٍ يَعْلَمُونَ ﴿٩٧ الأنعام﴾",
                R.raw.stars_2));
        ayaList.add(new Aya("إِنَّ رَبَّكُمُ اللَّهُ الَّذِي خَلَقَ السَّمَاوَاتِ وَالْأَرْضَ فِي سِتَّةِ أَيَّامٍ ثُمَّ اسْتَوَىٰ عَلَى الْعَرْشِ يُغْشِي اللَّيْلَ النَّهَارَ يَطْلُبُهُ حَثِيثًا وَالشَّمْسَ وَالْقَمَرَ وَالنُّجُومَ مُسَخَّرَاتٍ بِأَمْرِهِ ۗ أَلَا لَهُ الْخَلْقُ وَالْأَمْرُ ۗ تَبَارَكَ اللَّهُ رَبُّ الْعَالَمِينَ ﴿٥٤ الأعراف﴾",
                R.raw.stars_3));
        ayaList.add(new Aya("وَسَخَّرَ لَكُمُ اللَّيْلَ وَالنَّهَارَ وَالشَّمْسَ وَالْقَمَرَ ۖ وَالنُّجُومُ مُسَخَّرَاتٌ بِأَمْرِهِ ۗ إِنَّ فِي ذَٰلِكَ لَآيَاتٍ لِّقَوْمٍ يَعْقِلُونَ ﴿١٢ النحل﴾",
                R.raw.stars_4));
        ayaList.add(new Aya("وَعَلَامَاتٍ ۚ وَبِالنَّجْمِ هُمْ يَهْتَدُونَ ﴿١٦ النحل﴾",
                R.raw.stars_5));
        ayaList.add(new Aya("أَلَمْ تَرَ أَنَّ اللَّهَ يَسْجُدُ لَهُ مَن فِي السَّمَاوَاتِ وَمَن فِي الْأَرْضِ وَالشَّمْسُ وَالْقَمَرُ وَالنُّجُومُ وَالْجِبَالُ وَالشَّجَرُ وَالدَّوَابُّ وَكَثِيرٌ مِّنَ النَّاسِ ۖ وَكَثِيرٌ حَقَّ عَلَيْهِ الْعَذَابُ ۗ وَمَن يُهِنِ اللَّهُ فَمَا لَهُ مِن مُّكْرِمٍ ۚ إِنَّ اللَّهَ يَفْعَلُ مَا يَشَاءُ ﴿١٨ الحج﴾",
                R.raw.stars_6));
        ayaList.add(new Aya("فَنَظَرَ نَظْرَةً فِي النُّجُومِ ﴿٨٨ الصافات﴾",
                R.raw.stars_7));
        ayaList.add(new Aya("وَمِنَ اللَّيْلِ فَسَبِّحْهُ وَإِدْبَارَ النُّجُومِ ﴿٤٩ الطور﴾",
                R.raw.stars_8));
        ayaList.add(new Aya("وَالنَّجْمُ وَالشَّجَرُ يَسْجُدَانِ ﴿٦ الرحمن﴾",
                R.raw.stars_9));
        ayaList.add(new Aya("فَلَا أُقْسِمُ بِمَوَاقِعِ النُّجُومِ ﴿٧٥ الواقعة﴾",
                R.raw.stars_10));
        ayaList.add(new Aya("فَإِذَا النُّجُومُ طُمِسَتْ ﴿٨ المرسلات﴾",
                R.raw.stars_11));
        ayaList.add(new Aya("وَإِذَا النُّجُومُ انكَدَرَتْ ﴿٢ التكوير﴾",
                R.raw.stars_12));

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dilog_layout, null);
        alertDialog.setView(alertDialogView);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

        ImageView cancelIV = dialog.findViewById(R.id.imageView12);
        cancelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });

        RecyclerView plantesRecyclerView = dialog.findViewById(R.id.plantesRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        plantesRecyclerView.setLayoutManager(manager);

        AyaAdapter adapter = new AyaAdapter(context, ayaList);
        plantesRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
