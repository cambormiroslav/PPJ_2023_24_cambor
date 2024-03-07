import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
//jdk 21

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int limit = 7; //7-9 for array {1,2,3,4,5,6,7,8,9}
        int[] first_array = {1,2,3,4,5,6,7,8,9};
        int[] second_array = {1,2,3,4,5,6,7,8,9};
        int[] result = new int[first_array.length];
        System.out.println("**********************");
        System.out.println("***** Vector API *****");
        System.out.println("**********************");
        System.out.println();

        printArray(first_array);

        System.out.println("              +");

        printArray(second_array);

        System.out.println();

        //add two arrays
        var species = IntVector.SPECIES_PREFERRED;
        var vector1 = IntVector.fromArray(species,first_array,0);
        var vector2 = IntVector.fromArray(species, second_array,0);
        var result_vectors = vector1.add(vector2);
        result_vectors.intoArray(result,0);

        System.out.println("First method for add:");
        printArray(result);

        System.out.println();
        for(int index = 0; index < first_array.length;index+=species.length()){
            var mask = species.indexInRange(index,first_array.length);
            var vector1_cycle = IntVector.fromArray(species,first_array,index,mask);
            var vector2_cycle = IntVector.fromArray(species,second_array,index,mask);
            var result_vector2 = vector1_cycle.add(vector2_cycle,mask);
            result_vector2.intoArray(result, index,mask);
        }

        System.out.println("Second method for add:");
        printArray(result);

        System.out.println();
        double sum = 0d;
        for(int index = 0; index < first_array.length;index+=species.length()){
            var mask = species.indexInRange(index,first_array.length);
            var vector1_cycle = IntVector.fromArray(species,first_array,index,mask);
            var result_vector3 = vector1_cycle.mul(vector1_cycle, mask);
            result_vector3.intoArray(result, index,mask);
            sum += result_vector3.reduceLanes(VectorOperators.ADD,mask);
        }
        double norm_result = Math.sqrt(sum);

        System.out.println("Normalization method:");
        System.out.println(String.format("norm = %f",norm_result));

        System.out.println();
        double sum_for_averange = 0d;
        for(int index = 0; index < first_array.length;index+=species.length()){
            var mask = species.indexInRange(index,first_array.length);
            var vector1_cycle = IntVector.fromArray(species,first_array,index,mask);
            sum_for_averange += vector1_cycle.reduceLanes(VectorOperators.ADD,mask);
        }
        double avg = sum_for_averange / first_array.length;

        System.out.println("Averange method:");
        System.out.println(String.format("avg = %f",avg));


        //not working
        System.out.println();
        int max_index_greater_nums = 0;

        int[] result_for_greater_nums = new int[first_array.length];
        for(int index = 0; index < first_array.length;index+=species.length()){
            var mask = species.indexInRange(index,first_array.length);
            var vector1_cycle = IntVector.fromArray(species,first_array,index,mask);
            var cmp_greater = vector1_cycle.compare(VectorOperators.GT, limit, mask);
            vector1_cycle.compress(cmp_greater).intoArray(result_for_greater_nums, max_index_greater_nums);
            max_index_greater_nums += cmp_greater.trueCount();
        }

        System.out.println(String.format("Greater numbers (limit = %d):", limit));
        printArray(result_for_greater_nums);



        System.out.println();
        double sum_for_averange_greater_nums = 0d;
        int count = 0;
        for(int index = 0; index < first_array.length;index+=species.length()){
            var mask = species.indexInRange(index,first_array.length);
            var vector1_cycle = IntVector.fromArray(species,first_array,index,mask);
            var mask_gt_nums = vector1_cycle.compare(VectorOperators.GT, limit);
            //var cmp_lower = vector1_cycle.compare(VectorOperators.LT, limit, mask);
            sum += vector1_cycle.reduceLanes(VectorOperators.ADD,mask_gt_nums);
            count += mask_gt_nums.trueCount();
        }
        double averange_gt_nums = sum / count;

        System.out.println(String.format("Averange for greater numbers (limit = %d):", limit));
        System.out.println(String.format("avg = %f",averange_gt_nums));

    }

    private static void printArray(int[] array){
        System.out.print("[");
        for(int piece: array){
            System.out.print(String.format(" %s ",piece));
        }
        System.out.print("]");
        System.out.println();
    }
}