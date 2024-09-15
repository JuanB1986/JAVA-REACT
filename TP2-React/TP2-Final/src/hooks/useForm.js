import { useState } from 'react';

const useForm = (initialValues, validate) => {
    const [values, setValues] = useState(initialValues);
    const [errors, setErrors] = useState({});
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleChange = (event) => {
        const { name, value } = event.target;
        setValues({
            ...values,
            [name]: value,
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        setErrors(validate(values));
        setIsSubmitting(true);
    };

    return {
        values,
        errors,
        isSubmitting,
        handleChange,
        handleSubmit,
    };
};

export default useForm;