package com.test.a7minworkout

object Constants {
    fun getExercise() : ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val exercise1 = ExerciseModel(1, "Jumping Jacks",
            R.drawable.ic_jumping_jacks, false, false)
        exerciseList.add(exercise1)

        val exercise2 = ExerciseModel(2, "Standing High Knee Taps",
            R.drawable.ic_high_knees_running_in_place, false, false)
        exerciseList.add(exercise2)

        val exercise3 = ExerciseModel(3, "Push Up",
            R.drawable.ic_push_up, false, false)
        exerciseList.add(exercise3)

        val exercise4 = ExerciseModel(4, "Squat",
            R.drawable.ic_squat, false, false)
        exerciseList.add(exercise4)

        val exercise5 = ExerciseModel(5, "Ab Crunches",
            R.drawable.ic_abdominal_crunch, false, false)
        exerciseList.add(exercise5)

        val exercise6 = ExerciseModel(6, "Push Up And Rotation",
            R.drawable.ic_push_up_and_rotation, false, false)
        exerciseList.add(exercise6)

        val exercise7 = ExerciseModel(7, "Lunge",
            R.drawable.ic_lunge, false, false)
        exerciseList.add(exercise7)

        val exercise8 = ExerciseModel(8, "Side Plank Left Side",
            R.drawable.ic_side_plank_left, false, false)
        exerciseList.add(exercise8)

        val exercise9 = ExerciseModel(9, "Side Plank Right Side",
            R.drawable.ic_side_plank_right, false, false)
        exerciseList.add(exercise9)

        val exercise10 = ExerciseModel(10, "Bench Dips",
            R.drawable.ic_triceps_dip_on_chair, false, false)
        exerciseList.add(exercise10)

        val exercise11 = ExerciseModel(11, "Step Ups",
            R.drawable.ic_step_up_onto_chair, false, false)
        exerciseList.add(exercise11)

        val exercise12 = ExerciseModel(12, "Wall Sit",
            R.drawable.ic_wall_sit, false, false)
        exerciseList.add(exercise12)

        val exercise13 = ExerciseModel(13, "Plank",
            R.drawable.ic_plank, false, false)
        exerciseList.add(exercise13)

        return exerciseList
    }
}